package kr.pe.hkpark130.springblog.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //JpaRepository<Entity 클래스, PK타입> ->CRUD메소드 생성됨
    @Transactional
    @Query(value = "SELECT p.id, p.title, p.category, p.modified_date, COUNT(c.comment_id) AS count_comments FROM posts p LEFT JOIN comments c ON p.id = c.post_id GROUP BY p.id, c.post_id ORDER BY p.id DESC LIMIT :perPage OFFSET :page", nativeQuery = true)
    List<Object[]> findAllDesc(@Param("page") Integer page, @Param("perPage") Integer perPage);

    @Query(value = "SELECT p.id, p.title, p.category, p.modified_date, COUNT(c.comment_id) AS count_comments FROM posts p LEFT JOIN comments c ON p.id = c.post_id WHERE p.category = :category GROUP BY p.id, c.post_id ORDER BY p.id DESC LIMIT :perPage OFFSET :page", nativeQuery = true)
    List<Object[]> findCategoryDesc(@Param("category") String category, @Param("page") Integer page, @Param("perPage") Integer perPage);

    // 버전에 따라 (p.*) 안되는게 있음
//    @Query(value = "SELECT count(p.*) FROM Posts p", nativeQuery = true)
    @Query(value = "SELECT count(*) FROM posts p", nativeQuery = true)
    Integer getPageAllList();

//    @Query(value = "SELECT count(p.*) FROM Posts p WHERE p.category = :category", nativeQuery = true)
    @Query(value = "SELECT count(*) FROM posts p WHERE p.category = :category", nativeQuery = true)
    Integer getPageCategoryList(@Param("category") String category);

}
