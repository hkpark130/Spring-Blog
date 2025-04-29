package kr.p_e.hkpark130.springblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String content;
    private String guestName;
    private String password; // 게스트 댓글 비밀번호
    @JsonProperty(value = "isAdmin")
    private boolean isAdmin;
}
