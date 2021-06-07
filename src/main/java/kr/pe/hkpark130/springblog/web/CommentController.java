package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.domain.posts.Posts;
import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import kr.pe.hkpark130.springblog.service.comments.CommentsService;
import kr.pe.hkpark130.springblog.service.posts.PostsService;
import kr.pe.hkpark130.springblog.service.users.UserService;
import kr.pe.hkpark130.springblog.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentsService commentsService;
    private final EntityManager entityManager;

    @PostMapping("/api/comments/save")
    @ResponseBody
    public Long save(@RequestBody Map<String, Object> request) {
        Posts post_id = (request.get("post_id") != null)? entityManager.getReference( Posts.class, Long.valueOf(request.get("post_id").toString()) ):null;
        UserInfo user_id = (request.get("user_id") != null)? entityManager.getReference( UserInfo.class, Long.valueOf(request.get("user_id").toString()) ):null;
        Long parent_id = (request.get("parent_id") != null)? Long.valueOf(request.get("parent_id").toString()):null;
        String content = (request.get("comment") != null)? request.get("comment").toString():null;

        return commentsService.save(new CommentDto(post_id, user_id, parent_id, content));
    }

    @PutMapping("/api/comments/update/{id}")
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody CommentDto requestDto) {
        return commentsService.update(id, requestDto);
    }

    @DeleteMapping("/api/comments/delete/{id}")
    @ResponseBody
    public Long delete(@PathVariable Long id) {
        commentsService.delete(id);
        return id;
    }

}
