package kr.p_e.hkpark130.springblog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotionService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convertNotionToMarkdown(String notionUrl, String token) {
        try {
            // 노션 페이지 ID 추출
            String pageId = extractPageId(notionUrl);

            // Notion API 호출을 위한 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.set("Notion-Version", "2022-06-28"); // Notion API 버전

            // 먼저 페이지 정보를 가져와서 제목을 추출
            String pageApiUrl = "https://api.notion.com/v1/pages/" + pageId;
            ResponseEntity<String> pageResponse = restTemplate.exchange(
                    pageApiUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            // 페이지 제목 추출
            String title = extractPageTitle(pageResponse.getBody());
            StringBuilder markdown = new StringBuilder("# " + title + "\n");

            // 페이지의 블록 목록을 가져오기
            String blocksApiUrl = "https://api.notion.com/v1/blocks/" + pageId + "/children";
            ResponseEntity<String> blocksResponse = restTemplate.exchange(
                    blocksApiUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            // 블록 데이터를 마크다운으로 변환
            String blocksMarkdown = convertBlocksToMarkdown(blocksResponse.getBody(), headers);
            markdown.append(blocksMarkdown);

//            return markdown.toString();
            return blocksResponse.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Notion 콘텐츠 변환 실패: " + e.getMessage(), e);
        }
    }

    private String extractPageId(String notionUrl) {
        // URL에서 페이지 ID 추출
        Pattern pattern = Pattern.compile("([a-f0-9]{32})");
        Matcher matcher = pattern.matcher(notionUrl);

        if (matcher.find()) {
            return matcher.group(1);
        }

        // 다른 형식의 URL 처리 (예: notion.so/Page-Title-1234abcd)
        pattern = Pattern.compile("[^-]*$");
        matcher = pattern.matcher(notionUrl.split("\\?")[0]);

        if (matcher.find()) {
            String lastSegment = matcher.group();
            if (lastSegment.length() >= 32) {
                return lastSegment.substring(0, 32);
            }
        }

        throw new IllegalArgumentException("유효한 Notion URL 형식이 아닙니다.");
    }

    private String extractPageTitle(String pageJson) {
        try {
            JsonNode rootNode = objectMapper.readTree(pageJson);

            // 제목은 properties.title.title[0].plain_text 또는 properties.Name.title[0].plain_text에 위치
            JsonNode properties = rootNode.path("properties");

            // 제목 필드 찾기 (보통 "title" 또는 "Name")
            for (String fieldName : List.of("title", "Name", "name")) {
                if (properties.has(fieldName)) {
                    JsonNode titleNode = properties.path(fieldName).path("title");
                    if (titleNode.isArray() && titleNode.size() > 0) {
                        return titleNode.get(0).path("plain_text").asText("Untitled");
                    }
                }
            }

            return "Untitled"; // 제목을 찾지 못한 경우
        } catch (Exception e) {
            return "Untitled"; // JSON 파싱 오류
        }
    }

    private String convertBlocksToMarkdown(String blocksJson, HttpHeaders headers) {
        StringBuilder markdown = new StringBuilder();

        try {
            JsonNode rootNode = objectMapper.readTree(blocksJson);
            JsonNode results = rootNode.path("results");

            if (results.isArray()) {
                for (JsonNode block : results) {
                    String blockId = block.path("id").asText();
                    String blockType = block.path("type").asText();
                    JsonNode blockContent = block.path(blockType);

                    markdown.append(convertBlockToMarkdown(blockType, blockContent, blockId, headers)).append("\n\n");
                }
            }

            // 다음 페이지가 있는 경우 재귀 호출
            if (rootNode.has("next_cursor") && !rootNode.path("next_cursor").isNull()) {
                String nextCursor = rootNode.path("next_cursor").asText();
                String nextUrl = rootNode.path("next_url").asText() + "?start_cursor=" + nextCursor;

                ResponseEntity<String> nextBlocksResponse = restTemplate.exchange(
                        nextUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class
                );

                markdown.append(convertBlocksToMarkdown(nextBlocksResponse.getBody(), headers));
            }
        } catch (Exception e) {
            markdown.append("Error parsing blocks: ").append(e.getMessage());
        }

        return markdown.toString();
    }

    private String convertBlockToMarkdown(String blockType, JsonNode blockContent, String blockId, HttpHeaders headers) {
        switch (blockType) {
            case "paragraph":
                return convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "heading_1":
                return "# " + convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "heading_2":
                return "## " + convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "heading_3":
                return "### " + convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "bulleted_list_item":
                return "- " + convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "numbered_list_item":
                return "1. " + convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "to_do":
                boolean checked = blockContent.path("checked").asBoolean();
                return (checked ? "- [x] " : "- [ ] ") + convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "toggle":
                return convertRichTextToMarkdown(blockContent.path("rich_text")) + " (toggle)";

            case "code":
                String language = blockContent.path("language").asText("plaintext");
                return "```" + language + "\n" +
                        convertRichTextToMarkdown(blockContent.path("rich_text")) +
                        "\n```";

            case "quote":
                return "> " + convertRichTextToMarkdown(blockContent.path("rich_text"));

            case "divider":
                return "---";

            case "image":
                String caption = "";
                if (blockContent.has("caption")) {
                    caption = convertRichTextToMarkdown(blockContent.path("caption"));
                }

                String url = "";
                if (blockContent.has("file") && blockContent.path("file").has("url")) {
                    url = blockContent.path("file").path("url").asText();
                } else if (blockContent.has("external") && blockContent.path("external").has("url")) {
                    url = blockContent.path("external").path("url").asText();
                }

                return "![" + caption + "](" + url + ")";

            case "child_page":
                return "[" + blockContent.path("title").asText() + "](Subpage)";

            case "child_database":
                return "[Database: " + blockContent.path("title").asText() + "]";

            default:
                return "Unsupported block type: " + blockType;
        }
    }

    private String convertRichTextToMarkdown(JsonNode richTextArray) {
        if (!richTextArray.isArray() || richTextArray.size() == 0) {
            return "";
        }

        StringBuilder text = new StringBuilder();

        for (JsonNode textNode : richTextArray) {
            String plainText = textNode.path("plain_text").asText();
            JsonNode annotations = textNode.path("annotations");

            // 강조 효과 적용
            if (annotations.path("bold").asBoolean()) {
                plainText = "**" + plainText + "**";
            }

            if (annotations.path("italic").asBoolean()) {
                plainText = "*" + plainText + "*";
            }

            if (annotations.path("strikethrough").asBoolean()) {
                plainText = "~~" + plainText + "~~";
            }

            if (annotations.path("code").asBoolean()) {
                plainText = "`" + plainText + "`";
            }

            // 링크 처리
            if (textNode.has("href") && !textNode.path("href").isNull()) {
                plainText = "[" + plainText + "](" + textNode.path("href").asText() + ")";
            }

            text.append(plainText);
        }

        return text.toString();
    }
}