package kr.p_e.hkpark130.springblog.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    // 이메일 필드 추가 (nullable = true로 설정)
    // @Column(nullable = true)
    // private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        // this.email = email;
        this.role = role;
    }

    public String getRoleKey() {
        return role.name();
    }
}
