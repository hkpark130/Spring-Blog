package kr.p_e.hkpark130.springblog.repository;

import kr.p_e.hkpark130.springblog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    // author와 category를 함께 로드하여 N+1 문제 방지 (EntityGraph 사용)
    @EntityGraph(attributePaths = {"author", "category"})
    Page<Post> findAll(Pageable pageable);
    
    // 단일 게시글 조회 시 author와 category를 함께 로드
    @EntityGraph(attributePaths = {"author", "category"})
    Optional<Post> findById(Long id);
    
    // 게시글 ID 목록으로 한 번에 여러 게시글 조회 (배치 처리에 유용)
    @EntityGraph(attributePaths = {"author", "category"})
    List<Post> findByIdIn(List<Long> ids);

    // PostRepository에 검색 메소드 추가
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    Page<Post> findByTitleOrContentContaining(@Param("keyword") String keyword, Pageable pageable);

    Page<Post> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE (p.title LIKE %:keyword% OR p.content LIKE %:keyword%) AND p.category.id = :categoryId")
    Page<Post> findByTitleContainingOrContentContainingAndCategoryId(
            @Param("keyword") String keyword,
            @Param("categoryId") Long categoryId,
            Pageable pageable);
}
