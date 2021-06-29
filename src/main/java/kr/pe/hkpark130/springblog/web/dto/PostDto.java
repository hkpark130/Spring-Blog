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
    private String category;
    private LocalDateTime modifiedDate;
    private Long count_comments;

    @Builder
    public PostDto(String title, String contents, String category) {
        this.title = title;
        this.contents = contents;
        this.category = category;
    }

    public PostDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.category = entity.getCategory();
        this.modifiedDate = entity.getModifiedDate();
    } // 상세보기를 위한 생성자 findById

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .contents(contents)
                .category(category)
                .build();
    } //repository로 저장을 위해

    public PostDto(Long id, String title, String category, LocalDateTime modifiedDate, Long count_comments){
        this.id = id;
        this.title = title;
        this.category = category;
        this.modifiedDate = modifiedDate;
        this.count_comments = count_comments;
    } // 인덱스 화면에 뿌려주기 위한 생성자

}
