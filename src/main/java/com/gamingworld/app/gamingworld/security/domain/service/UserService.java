package com.gamingworld.app.gamingworld.security.domain.service;

import com.gamingworld.app.gamingworld.security.domain.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();

    public Optional<User> findById(Long id);

    public Optional<User> findByEmail(String id);

    public List<User> getAllByUsername(String username);

    public User save(User entity);
}
