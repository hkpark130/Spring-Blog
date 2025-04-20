package kr.p_e.hkpark130.springblog.controller;

import kr.p_e.hkpark130.springblog.auth.UserPrincipal;
import kr.p_e.hkpark130.springblog.dto.CommentRequestDto;
import kr.p_e.hkpark130.springblog.dto.CommentsResponseDto;
import kr.p_e.hkpark130.springblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    
    @GetMapping
    public ResponseEntity<CommentsResponseDto> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }
    
    @PostMapping
    public ResponseEntity<Long> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal UserPrincipal principal // 수정된 부분 - expression 제거
    ) {
        String username = principal != null ? principal.getUsername() : null;
        return ResponseEntity.ok(commentService.createComment(postId, dto, username));
    }
    
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestParam(required = false) String password,
            @AuthenticationPrincipal UserPrincipal principal // 수정된 부분 - expression 제거
    ) {
        String username = principal != null ? principal.getUsername() : null;
        commentService.deleteComment(commentId, password, username);
        return ResponseEntity.ok().build();
    }
}
