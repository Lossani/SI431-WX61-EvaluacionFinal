package com.gamingworld.app.gamingworld.security.domain.persistence;

import com.gamingworld.app.gamingworld.security.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
