package kr.p_e.hkpark130.springblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostsResponseDto {
    private List<PostResponseDto> posts;
    private int total;
    private int page;
    private int size;
}
