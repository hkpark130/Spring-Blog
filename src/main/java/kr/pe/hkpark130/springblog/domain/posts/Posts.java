package kr.pe.hkpark130.springblog.domain.posts;

import kr.pe.hkpark130.springblog.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(columnDefinition = "varchar(255) default 'etc'")
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
