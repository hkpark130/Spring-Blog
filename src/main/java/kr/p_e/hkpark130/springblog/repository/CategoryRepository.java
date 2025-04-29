package kr.p_e.hkpark130.springblog.repository;

import kr.p_e.hkpark130.springblog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
