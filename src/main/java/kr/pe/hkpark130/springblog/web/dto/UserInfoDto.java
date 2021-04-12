package kr.pe.hkpark130.springblog.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    private String username;
    private String password;

    private String auth;
}
