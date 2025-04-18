package kr.p_e.hkpark130.springblog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/panel")
    public ResponseEntity<String> adminPanel() {
        return ResponseEntity.ok("🎉 어드민 권한 확인 완료. 접근 허용!");
    }
}
