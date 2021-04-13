package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.service.posts.PostsService;
import kr.pe.hkpark130.springblog.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        String username = (String) httpSession.getAttribute("username");

        if (username != null){
            model.addAttribute("user", username);
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable Long id, Model model) {
        PostDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-view";
    }

}
