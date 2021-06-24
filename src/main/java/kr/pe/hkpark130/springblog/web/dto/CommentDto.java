package kr.pe.hkpark130.springblog.web.dto;

import kr.pe.hkpark130.springblog.domain.comment.Comments;
import kr.pe.hkpark130.springblog.domain.posts.Posts;
import kr.pe.hkpark130.springblog.domain.posts.PostsRepository;
import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import kr.pe.hkpark130.springblog.service.posts.PostsService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
    private Long comment_id;
    private Posts post_id;
    private UserInfo user_id;
    private Long parent_id;
    private String comment;
    private String password;

    @Builder
    public CommentDto(Posts post_id, UserInfo user_id, Long parent_id, String comment, String password) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.parent_id = parent_id;
        this.comment = comment;
        this.password = password;
    }

    public CommentDto(Comments entity){
        this.comment_id = entity.getComment_id();
        this.post_id = entity.getPost_id();
        this.user_id = entity.getUser_id();
        this.parent_id = entity.getParent_id();
        this.comment = entity.getComment();
        this.password = entity.getPassword();
    }

    public Comments toEntity() {
        return Comments.builder()
                .post_id(post_id)
                .user_id(user_id)
                .parent_id(parent_id)
                .comment(comment)
                .password(password)
                .build();
    }

}
