package kr.p_e.hkpark130.springblog.controller;

import kr.p_e.hkpark130.springblog.auth.UserPrincipal;
import kr.p_e.hkpark130.springblog.dto.CommentRequestDto;
import kr.p_e.hkpark130.springblog.dto.CommentsResponseDto;
import kr.p_e.hkpark130.springblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
@Slf4j
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
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        String username = principal != null ? principal.getUsername() : null;
        return ResponseEntity.ok(commentService.createComment(postId, dto, username));
    }

    // 일반 사용자 댓글 수정 (로그인 필요)
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateUserComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        String username = principal != null ? principal.getUsername() : null;
        commentService.updateUserComment(commentId, dto, username);
        return ResponseEntity.ok().build();
    }

    // 게스트 댓글 수정 (비밀번호 필요, 인증 불필요)
    @PutMapping("/{commentId}/guest")
    public ResponseEntity<Void> updateGuestComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto
    ) {
        // 비밀번호는 dto에 포함되어 있음
        commentService.updateGuestComment(commentId, dto);
        return ResponseEntity.ok().build();
    }

    // 일반 사용자 댓글 삭제 (로그인 필요)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteUserComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        String username = principal != null ? principal.getUsername() : null;
        commentService.deleteUserComment(postId, commentId, username);
        return ResponseEntity.ok().build();
    }

    // 게스트 댓글 삭제 (비밀번호 필요, 인증 불필요)
    @DeleteMapping("/{commentId}/guest")
    public ResponseEntity<Void> deleteGuestComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto
    ) {
        commentService.deleteGuestComment(postId, commentId, dto);
        return ResponseEntity.ok().build();
    }

    // 비밀번호 검증 (프론트에서 사용자 경험 개선을 위해 선택적으로 사용)
    @PostMapping("/{commentId}/verify")
    public ResponseEntity<Map<String, Object>> verifyCommentPassword(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody Map<String, String> passwordMap
    ) {
        String password = passwordMap.get("password");
        boolean isValid = commentService.validatePassword(commentId, password);

        Map<String, Object> response = new HashMap<>();
        response.put("valid", isValid);

        if (isValid) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}