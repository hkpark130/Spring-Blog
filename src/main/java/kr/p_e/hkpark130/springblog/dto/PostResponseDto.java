package kr.p_e.hkpark130.springblog.dto;

import kr.p_e.hkpark130.springblog.domain.Post; // 이 import 추가
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor // 모든 필드를 포함하는 생성자
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int commentCount; // 댓글 수
    
    // 기존 생성자와 호환되도록 추가 (PostService에서 사용)
    public PostResponseDto(Long id, String title, String content, String category, String author, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.createdAt = null; // 문자열이 아닌 LocalDateTime 으로 저장하려면 파싱 로직 필요
        this.updatedAt = null;
        this.commentCount = 0; // 기본값
    }

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .category(post.getCategory().getName())
            .author(post.getAuthor().getUsername())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .commentCount(post.getComments().size()) // 댓글 수 설정
            .build();
    }
}
