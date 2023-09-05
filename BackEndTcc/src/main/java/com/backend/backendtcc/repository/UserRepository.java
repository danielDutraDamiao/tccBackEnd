package com.backend.backendtcc.repository;

import com.backend.backendtcc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username = ?1 limit 1", nativeQuery = true)
    User findByUsername(String username);

}
