package kr.p_e.hkpark130.springblog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private Long categoryId;
}