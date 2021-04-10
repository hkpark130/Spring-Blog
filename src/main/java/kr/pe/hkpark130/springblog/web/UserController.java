package kr.pe.hkpark130.springblog.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

//    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
