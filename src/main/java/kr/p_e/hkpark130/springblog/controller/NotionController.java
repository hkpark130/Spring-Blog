package kr.p_e.hkpark130.springblog.controller;

import kr.p_e.hkpark130.springblog.dto.NotionUrlRequestDto;
import kr.p_e.hkpark130.springblog.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notion")
public class NotionController {
    private final NotionService notionService;

    @PostMapping("/convert")
    public ResponseEntity<String> convertNotionToMarkdown(@RequestBody NotionUrlRequestDto request) {
        String markdown = notionService.convertNotionToMarkdown(request.getUrl(), request.getToken());
        return ResponseEntity.ok(markdown);
    }
}