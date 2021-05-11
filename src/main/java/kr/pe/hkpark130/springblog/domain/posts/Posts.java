package kr.pe.hkpark130.springblog.domain.posts;

import kr.pe.hkpark130.springblog.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "posts")
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 500, nullable = false, name = "title")
    private String title;

    @Column(columnDefinition = "TEXT", name = "contents")
    private String contents;

    @Column(columnDefinition = "varchar(255) default 'etc'", name = "category")
    private String category;

    @Builder
    public Posts(String title, String contents, String category){
        this.title = title;
        this.contents = contents;
        this.category = category;
    };

    public void update(String title, String contents, String category){
        this.title = title;
        this.contents = contents;
        this.category = category;
    }

}
