package kr.pe.hkpark130.springblog.domain.comment;

import kr.pe.hkpark130.springblog.domain.posts.Posts;
import kr.pe.hkpark130.springblog.domain.BaseTimeEntity;
import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "comments")
@Table(name = "comments")
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long comment_id;

    @ManyToOne
    @JoinColumn(name ="post_id", referencedColumnName = "id")
    private Posts post_id;

    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private UserInfo user_id;

    @Column(name = "parent_id")
    private Long parent_id;

    @Column(columnDefinition = "TEXT", name = "comment")
    private String comment;

    @Column(name = "password")
    private String password;

    @Column(name = "likes")
    private Long likes;

    @Builder
    public Comments(Posts post_id, UserInfo user_id, Long parent_id, String comment, String password, Long likes){
        this.post_id = post_id;
        this.user_id = user_id;
        this.parent_id = parent_id;
        this.comment = comment;
        this.password = password;
        this.likes = likes;
    };

    public void update(String comment){
        this.comment = comment;
    }
}
