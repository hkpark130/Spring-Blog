package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("posts", postsService.findAllDesc());
        String username = (String) httpSession.getAttribute("username");

        if (username != null){
            model.addAttribute("user", username);
        }
        return "index";
    }


}
