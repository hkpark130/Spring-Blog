package kr.p_e.hkpark130.springblog.repository;

import kr.p_e.hkpark130.springblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
