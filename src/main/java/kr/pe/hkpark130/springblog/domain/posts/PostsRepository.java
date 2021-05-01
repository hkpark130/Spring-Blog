package kr.pe.hkpark130.springblog.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //JpaRepository<Entity 클래스, PK타입> ->CRUD메소드 생성됨
    @Transactional
    @Query(value = "SELECT p.* FROM Posts p ORDER BY p.id DESC LIMIT :perPage OFFSET :page", nativeQuery = true)
    List<Posts> findAllDesc(@Param("page") Integer page, @Param("perPage") Integer perPage);

    @Query(value = "SELECT p.* FROM Posts p WHERE p.category = :category ORDER BY p.id DESC LIMIT :perPage OFFSET :page", nativeQuery = true)
    List<Posts> findCategoryDesc(@Param("category") String category, @Param("page") Integer page, @Param("perPage") Integer perPage);

    @Query(value = "SELECT count(p.*) FROM Posts p", nativeQuery = true)
    Integer getPageAllList();

    @Query(value = "SELECT count(p.*) FROM Posts p WHERE p.category = :category", nativeQuery = true)
    Integer getPageCategoryList(@Param("category") String category);

}
