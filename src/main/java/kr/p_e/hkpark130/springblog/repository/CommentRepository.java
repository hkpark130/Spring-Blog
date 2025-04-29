package kr.p_e.hkpark130.springblog.repository;

import kr.p_e.hkpark130.springblog.domain.Comment;
import kr.p_e.hkpark130.springblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtDesc(Post post);
    
    // 게시글의 댓글 수를 효율적으로 조회하는 메소드 추가
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post.id = :postId")
    int countByPostId(@Param("postId") Long postId);
}
