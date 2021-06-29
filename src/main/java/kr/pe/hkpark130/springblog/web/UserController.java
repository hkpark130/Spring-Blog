package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import kr.pe.hkpark130.springblog.service.users.UserService;
import kr.pe.hkpark130.springblog.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/user")
    public String signup(UserInfoDto infoDto) { // 회원 추가
        userService.save(infoDto);
        return "redirect:/login";
    }

    @PostMapping("/api/session_check")
    @ResponseBody
    public Long session_check(@RequestBody Map<String, Object> request) {
        Long user_id = (request.get("user_id") != null)? Long.valueOf(request.get("user_id").toString()) :null;
        if(user_id == null){
            return 0L;
        }
        UserInfo user = (UserInfo) httpSession.getAttribute("user");
        if ( user != null && user_id.equals(user.getId()) ){
            return 1L;
        } else {
            return 0L;
        }
    }
}
