package kr.p_e.hkpark130.springblog.controller;

import kr.p_e.hkpark130.springblog.auth.UserPrincipal;
import kr.p_e.hkpark130.springblog.dto.PostRequestDto;
import kr.p_e.hkpark130.springblog.dto.PostResponseDto;
import kr.p_e.hkpark130.springblog.dto.PostsResponseDto;
import kr.p_e.hkpark130.springblog.dto.PostSitemapDto;
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
            @AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(postService.createPost(dto, principal.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    // PostController에 검색 파라미터 추가
    @GetMapping
    public ResponseEntity<PostsResponseDto> getPosts(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long categoryId) {

        // 검색어와 카테고리 모두 있는 경우
        if (search != null && !search.isEmpty() && categoryId != null) {
            return ResponseEntity.ok(postService.searchPostsBySearchAndCategory(search, categoryId, offset, limit));
        }
        // 검색어만 있는 경우
        else if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(postService.searchPosts(search, offset, limit));
        }
        // 카테고리만 있는 경우
        else if (categoryId != null) {
            return ResponseEntity.ok(postService.getPostsByCategory(categoryId, offset, limit));
        }
        // 모두 없는 경우 (전체 목록)
        else {
            return ResponseEntity.ok(postService.getPosts(offset, limit));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto dto,
            @AuthenticationPrincipal UserPrincipal principal) {
        postService.updatePost(id, dto, principal.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal) {
        postService.deletePost(id, principal.getUsername());
        return ResponseEntity.ok().build();
    }

    // 전체 게시글을 사이트맵용 최소 필드로 반환 (내부적으로 페이징 반복)
    @GetMapping("/sitemap/all")
    public ResponseEntity<List<PostSitemapDto>> getAllForSitemap() {
        return ResponseEntity.ok(postService.findAllForSitemap());
    }
}
