package kr.p_e.hkpark130.springblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String content;
    private String guestName;
    private String password; // 게스트 댓글 비밀번호
}
