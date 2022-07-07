package com.gamingworld.app.gamingworld.security.service;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.Profile;
import com.gamingworld.app.gamingworld.profile.domain.persitence.ProfileRepository;
import com.gamingworld.app.gamingworld.security.domain.model.entity.User;
import com.gamingworld.app.gamingworld.security.domain.persistence.UserRepository;
import com.gamingworld.app.gamingworld.security.domain.service.UserService;
import com.gamingworld.app.gamingworld.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllByUsername(String username) {
        return this.userRepository.findAll().stream().filter(o-> o.getUsername().toLowerCase().contains(username.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public User save(User entity){

        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        entity.addAuthority("ROLE_USER");

        User newUser = userRepository.save(entity);
        Profile newProfile = new Profile();
        newProfile.setUser(newUser);

        profileRepository.save(newProfile);
        return newUser;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retornando el usuario
        Optional<User> userFound = this.userRepository.findByUsername(username);

        return userFound.orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }
}
