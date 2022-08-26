package com.blog.repository;

import com.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

// JPA Naming 전략
// SELECT * FROM user WHERE username=?1 AND password=?2;
//    User findByUsernameAndPassword(String email, String password);

// 밑의 방법으로도 사용 가능
//    @Query(value = "SELECT * FROM user WHERE email=?1 AND password=?2", nativeQuery = true)
//    User login(String email, String password);
