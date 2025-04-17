package kr.p_e.hkpark130.springblog.controller;

import kr.p_e.hkpark130.springblog.auth.UserPrincipal;
import kr.p_e.hkpark130.springblog.domain.User;
import kr.p_e.hkpark130.springblog.dto.*;
import kr.p_e.hkpark130.springblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(Authentication authentication) {
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        userService.logout(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody RefreshRequestDto request) {
        return ResponseEntity.ok(userService.refresh(request));
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
