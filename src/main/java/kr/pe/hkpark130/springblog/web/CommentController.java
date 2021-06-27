package kr.pe.hkpark130.springblog.web;

import kr.pe.hkpark130.springblog.domain.posts.Posts;
import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import kr.pe.hkpark130.springblog.service.comments.CommentsService;
import kr.pe.hkpark130.springblog.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentsService commentsService;
    private final EntityManager entityManager;

    private final HttpSession httpSession;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostMapping("/api/comments/save")
    @ResponseBody
    public Long save(@RequestBody Map<String, Object> request) {
        Posts post_id = (request.get("post_id") != null)? entityManager.getReference( Posts.class, Long.valueOf(request.get("post_id").toString()) ):null;
        UserInfo user_id = (request.get("user_id") != null)? entityManager.getReference( UserInfo.class, Long.valueOf(request.get("user_id").toString()) ):null;
        Long parent_id = (request.get("parent_id") != null)? Long.valueOf(request.get("parent_id").toString()):null;
        String content = (request.get("comment") != null)? request.get("comment").toString():null;
        String password = (request.get("password") != null)? request.get("password").toString():"";
        String encodedPassword = passwordEncoder.encode(password);
        Long likes = 0L;

        return commentsService.save(new CommentDto(post_id, user_id, parent_id, content, encodedPassword, likes));
    }

    @PutMapping("/api/comments/update/{id}")
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody CommentDto requestDto) throws Exception {
        is_owner(id);
        return commentsService.update(id, requestDto);
    }

    @DeleteMapping("/api/comments/delete/{id}")
    @ResponseBody
    public Long delete(@PathVariable Long id) throws Exception {
        is_owner(id);
        commentsService.delete(id);
        return id;
    }

    @PostMapping("/api/comments/check_password/{id}")
    @ResponseBody
    public Long checkPassword(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        String password = (request.get("password") != null)? request.get("password").toString():"";
        CommentDto commentDto = commentsService.findById(id);
        if ( passwordEncoder.matches(password, commentDto.getPassword()) ){
            return 1L;
        }else{
            return 0L;
        }
    }

    public void is_owner(Long id) throws Exception {
        UserInfo user = (UserInfo) httpSession.getAttribute("user");
        CommentDto commentDto = commentsService.findById(id);

        if (user != null){
            if( commentDto.getUser_id() != null && !commentDto.getUser_id().getId().equals(user.getId()) ){
                throw new Exception("현재 접속중인 유저는 권한이 없습니다.");
            }
        } else if(commentDto.getUser_id() != null){
            throw new Exception("게스트는 권한이 없습니다.");
        }
    }

}
