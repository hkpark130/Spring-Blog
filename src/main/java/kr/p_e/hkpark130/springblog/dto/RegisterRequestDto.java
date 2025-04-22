package kr.p_e.hkpark130.springblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "사용자명은 필수 입력값입니다.")
    @Size(min = 3, max = 20, message = "사용자명은 3~20자 사이여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
    private String password;

    // 이메일 필드를 선택적으로 변경 (@NotBlank 제거)
    // @Email(message = "유효한 이메일 형식이 아닙니다.")
    // private String email;
}
