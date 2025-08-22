package kr.p_e.hkpark130.springblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSitemapDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
