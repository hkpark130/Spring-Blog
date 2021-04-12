package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.service.posts.PostsService;
import kr.pe.hkpark130.springblog.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostsService postsService;

    @PostMapping("/posts/save")
    public Long save(@RequestBody PostDto requestDto) {
        return postsService.save(requestDto);
    }

}
