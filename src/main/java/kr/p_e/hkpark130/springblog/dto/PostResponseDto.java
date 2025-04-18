package kr.p_e.hkpark130.springblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String categoryName;
    private String author;
    private String createdAt;
    private String updatedAt;
}