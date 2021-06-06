package kr.pe.hkpark130.springblog.web.dto;

import kr.pe.hkpark130.springblog.domain.comment.Comments;
import kr.pe.hkpark130.springblog.domain.posts.Posts;
import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentDto {
    private Long comment_id;
    private Posts post_id;
    private UserInfo user_id;
    private Long parent_id;
    private String content;

    @Builder
    public CommentDto(Posts post_id, UserInfo user_id, Long parent_id, String content) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.parent_id = parent_id;
        this.content = content;
    }

    public CommentDto(Comments entity){
        this.comment_id = entity.getComment_id();
        this.post_id = entity.getPost_id();
        this.user_id = entity.getUser_id();
        this.parent_id = entity.getParent_id();
        this.content = entity.getContent();
    }

    public Comments toEntity() {
        return Comments.builder()
                .post_id(post_id)
                .user_id(user_id)
                .parent_id(parent_id)
                .content(content)
                .build();
    }

}
