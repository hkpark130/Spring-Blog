package kr.p_e.hkpark130.springblog.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @Column(name = "username", length = 191)
    private String username;

    @Column(nullable = false)
    private String token;

    public RefreshToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public void update(String newToken) {
        this.token = newToken;
    }
}