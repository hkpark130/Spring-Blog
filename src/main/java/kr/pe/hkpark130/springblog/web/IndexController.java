package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import kr.pe.hkpark130.springblog.service.comments.CommentsService;
import kr.pe.hkpark130.springblog.service.posts.PostsService;
import kr.pe.hkpark130.springblog.web.dto.CommentDto;
import kr.pe.hkpark130.springblog.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final HttpSession httpSession;
    private static final int PAGE_BLOCK = 5;  // 하단 페이지 리스트 수
    private static final int PER_PAGE = 10;   // 한 페이지에 보여줄 게시글 수

    @GetMapping({"/", "/etc", "/network", "/work", "/programming", "/cs"})
    public String index(Model model, HttpServletRequest request, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        String category = request.getRequestURI().substring(1);
        Integer offsetPage = (pageNum - 1) * PER_PAGE;
        List<PostDto> postList;

        if(category.length() == 0){
            postList = postsService.findAllDesc(offsetPage, PER_PAGE);
        } else {
            postList = postsService.findCategoryDesc(category, offsetPage, PER_PAGE);
        }

        TreeMap<Integer, String> pageList = postsService.getPageAllList(category, PER_PAGE, PAGE_BLOCK, pageNum);
        model.addAttribute("posts", postList);
        model.addAttribute("pageList", pageList);

        UserInfo user = (UserInfo) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("user", user);
        }

        model.addAttribute("activePage", pageNum);
        model.addAttribute("category", category);
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
        PostDto postDto = postsService.findById(id);
        model.addAttribute("post", postDto);

        List<CommentDto> commentList;
        commentList = commentsService.findAll(id);
        model.addAttribute("comments", commentList);

        UserInfo user = (UserInfo) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("user", user);
        }


        return "posts-view";
    }

}
