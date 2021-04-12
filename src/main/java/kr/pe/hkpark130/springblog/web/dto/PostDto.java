package kr.pe.hkpark130.springblog.web.dto;

import kr.pe.hkpark130.springblog.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime modifiedDate;

    @Builder
    public PostDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .contents(contents)
                .build();
    } //repository로 저장을 위해
}
