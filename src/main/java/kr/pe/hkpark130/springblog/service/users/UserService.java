package kr.pe.hkpark130.springblog.service.users;

import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import kr.pe.hkpark130.springblog.domain.users.UserRepository;
import kr.pe.hkpark130.springblog.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param username 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException((username)));
    } //유저 없으면 에러처리 (필수구현)

    public UserInfo findById(Long user_id) throws UsernameNotFoundException {
        return userRepository.findById(user_id)
                .orElseThrow(() -> new UsernameNotFoundException(("해당 유저 없음")));
    }

    /**
     * 회원정보 저장
     *
     * @param infoDto 회원정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    public Long save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return userRepository.save(UserInfo.builder()
                .username(infoDto.getUsername())
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword()).build()).getId();
    }

}
