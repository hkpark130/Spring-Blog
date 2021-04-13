package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.service.posts.PostsService;
import kr.pe.hkpark130.springblog.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostsService postsService;

    @PostMapping("/api/posts/save")
    @ResponseBody
    public Long save(@RequestBody PostDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/posts/update/{id}")
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody PostDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/delete/{id}")
    @ResponseBody
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
