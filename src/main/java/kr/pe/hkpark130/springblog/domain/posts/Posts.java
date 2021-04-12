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

    @Builder
    public Posts(String title, String contents){
        this.title = title;
        this.contents = contents;
    };

    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

}
