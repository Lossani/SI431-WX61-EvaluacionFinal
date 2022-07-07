package com.gamingworld.app.gamingworld.security.config.api;

import com.gamingworld.app.gamingworld.security.domain.model.entity.User;
import com.gamingworld.app.gamingworld.security.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/users")
@Tag(name = "User Controller", description = "Operations for User data.")
public class UserController {
    
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(path = "")
    @Operation(summary = "Retrieves all existing users.", description = "Retrieves all existing users.")
    public @ResponseBody List<User> getAll(){
        return userService.getAll();

    }

    @GetMapping(path = "/find")
    @Operation(summary = "Finds a user by their username.", description = "Finds a user by their username.")
    public @ResponseBody List<User> getAllByUsername(@RequestParam String username){
        return userService.getAllByUsername(username);
    }

    @GetMapping(path = "/email")
    @Operation(summary = "Retrieves a user by their email.", description = "Retrieves a user by their email.")
    public @ResponseBody
    Optional<User> getByEmail(@RequestParam String email){
        return userService.findByEmail(email);
    }

    @PostMapping(path = "/signup",consumes = "application/json", produces = "application/json")
    @Operation(summary = "Request for sign up a new user.", description = "Request for sign up a new user.")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }
}
