package kr.p_e.hkpark130.springblog.service;

import kr.p_e.hkpark130.springblog.auth.JWTTokenProvider;
import kr.p_e.hkpark130.springblog.domain.RefreshToken;
import kr.p_e.hkpark130.springblog.domain.Role;
import kr.p_e.hkpark130.springblog.domain.User;
import kr.p_e.hkpark130.springblog.dto.*;
import kr.p_e.hkpark130.springblog.repository.RefreshTokenRepository;
import kr.p_e.hkpark130.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        String access = tokenProvider.createToken(user.getUsername(), user.getRole());
        String refresh = tokenProvider.createRefreshToken(user.getUsername());

        refreshTokenRepository.save(new RefreshToken(user.getUsername(), refresh));

        return new LoginResponseDto(access, refresh);
    }

    public TokenResponseDto refresh(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new RuntimeException("리프레시 토큰 누락");
        }

        RefreshToken entity = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> {
                    return new RuntimeException("리프레시 토큰이 유효하지 않습니다.");
                });

        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("리프레시 토큰 만료됨.");
        }

        String username = tokenProvider.getUsername(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        String newAccess = tokenProvider.createToken(user.getUsername(), user.getRole());

        return new TokenResponseDto(newAccess);
    }

    public void register(RegisterRequestDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public UserResponseDto getProfile(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole().name());
    }

    public void logout(User user) {
        refreshTokenRepository.deleteById(user.getUsername());
    }
}
