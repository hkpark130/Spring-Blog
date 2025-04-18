package kr.p_e.hkpark130.springblog.controller;

import kr.p_e.hkpark130.springblog.auth.UserPrincipal;
import kr.p_e.hkpark130.springblog.dto.PostRequestDto;
import kr.p_e.hkpark130.springblog.dto.PostResponseDto;
import kr.p_e.hkpark130.springblog.dto.PostsResponseDto;
import kr.p_e.hkpark130.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> createPost(
            @RequestBody PostRequestDto dto,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        return ResponseEntity.ok(postService.createPost(dto, principal.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping
    public ResponseEntity<PostsResponseDto> getPosts(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(postService.getPosts(offset, limit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto dto,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        postService.updatePost(id, dto, principal.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        postService.deletePost(id, principal.getUsername());
        return ResponseEntity.ok().build();
    }
}
