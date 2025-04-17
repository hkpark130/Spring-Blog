package kr.p_e.hkpark130.springblog.auth;

import kr.p_e.hkpark130.springblog.repository.UserRepository;
import kr.p_e.hkpark130.springblog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 로그인 시 DB에서 유저를 조회하는 핵심 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        return new UserPrincipal(user);
    }
}