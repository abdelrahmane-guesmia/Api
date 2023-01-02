package com.snapface.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.snapface.api.models.User;
import com.snapface.api.services.UserService;

import java.util.Optional;

import static com.snapface.api.security.WebSecurityConfig.SECURITY_CONFIG_NAME;

@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "User", description = "User manipulation")
@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = SECURITY_CONFIG_NAME)
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Read - get all users
     * @return - An iterable object of user fulfilled
     */
    @GetMapping("")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * Read - get user
     * @param id - the id of the user
     * @return An user object fulfilled
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUser(id);
        return user.orElse(null);
    }

    /**
     * Create - Add a new user
     * @param user - An object user
     * @return The user object created
     */
    @PostMapping("")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Delete - Delete an user
     * @param id - The id of the user to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteUser(@PathVariable("id") final Long id) {
        userService.deleteUser(id);
    }

    /**
     * Update - Update an existing user
     * @param id - the id of the user to update
     * @param user - the user object to update
     * @return - Deleted user
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
        Optional<User> u = userService.getUser(id);
        if(u.isPresent()) {
            User currentUser = u.get();

            String firstName = user.getUsername();
            if(firstName != null) {
                currentUser.setUsername(firstName);
            }
            String mail = user.getEmail();
            if(mail != null) {
                currentUser.setEmail(mail);
            }
            String password = user.getPassword();
            if(password != null) {
                currentUser.setPassword(password);
            }
            userService.saveUser(currentUser);
            return currentUser;
        } else {
            return null;
        }
    }
}
