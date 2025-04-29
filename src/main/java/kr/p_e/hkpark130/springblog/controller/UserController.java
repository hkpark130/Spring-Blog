package kr.p_e.hkpark130.springblog.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.p_e.hkpark130.springblog.auth.UserPrincipal;
import kr.p_e.hkpark130.springblog.domain.User;
import kr.p_e.hkpark130.springblog.dto.*;
import kr.p_e.hkpark130.springblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request, HttpServletResponse response) {
        LoginResponseDto result = userService.login(request);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", result.getRefreshToken())
                .httpOnly(true)
//                .secure(false) // (HTTPS 필요)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(
                new LoginResponseDto(result.getAccessToken(), null)
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            Authentication authentication,
            HttpServletResponse response
    ) {
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        userService.logout(user);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(
            @CookieValue(value = "refreshToken", required = false) String refreshToken
    ) {
        return ResponseEntity.ok(userService.refresh(refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto request) {
        userService.register(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyInfo(Authentication authentication) {
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        return ResponseEntity.ok(userService.getProfile(user));
    }
}
