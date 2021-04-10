package kr.pe.hkpark130.springblog.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    private final PostsService postsService;
//    private final HttpSession httpSession;

    @GetMapping("/")
    public String index() {
//        model.addAttribute("posts", postsService.findAllDesc());
//        UserInfoDto user = (UserInfoDto) httpSession.getAttribute("user");
//        if (user != null){
//            model.addAttribute("user", user.getEmail());
//        }
        return "index";
    }


}
