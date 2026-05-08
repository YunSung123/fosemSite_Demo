package com.FosemDefense.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// user_tb 테이블에 접근하는 창구
public interface UserRepository extends JpaRepository<User, Integer> {

    // 사용자 명으로 사용자 조회
    @Query("""
        SELECT u FROM User u WHERE u.username = :username
    """)
    Optional<User> findByUsername(String username);

    // 사용자명과 비밀번호로 사용자 조회(로그인용)
    @Query("""
        SELECT u FROM User u WHERE u.username = :username AND u.password = :password 
    """)
    Optional<User> findByUsernameAndPassword(@Param("username") String username,
                                             @Param("password")  String password);
}
