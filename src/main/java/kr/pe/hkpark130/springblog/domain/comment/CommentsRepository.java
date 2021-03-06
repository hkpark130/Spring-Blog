package kr.pe.hkpark130.springblog.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Transactional
    @Query(value = "SELECT c.* FROM comments c WHERE c.post_id = :post_id", nativeQuery = true)
    List<Comments> findAll(@Param("post_id") Long post_id);

    @Transactional
    @Query(value = "SELECT count(c.*) FROM comments c WHERE c.comment_id = :comment_id AND c.password = :password", nativeQuery = true)
    Long checkPassword(@Param("comment_id") Long comment_id, @Param("password") String password);
}
