package com.snapface.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.snapface.api.model.User;
import com.snapface.api.service.UserService;

import java.util.Optional;

@Tag(name = "User", description = "User manipulation")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Read - get all users
     * @return - An iterable object of user fulfilled
     */
    @GetMapping("/user")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * Read - get user
     * @param id - the id of the user
     * @return An user object fulfilled
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userService.getUser(id);
        return user.orElse(null);
    }

    /**
     * Create - Add a new user
     * @param user - An object user
     * @return The user object created
     */
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Delete - Delete an user
     * @param id - The id of the user to delete
     */
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userService.deleteUser(id);
    }

    /**
     * Update - Update an existing user
     * @param id - the id of the user to update
     * @param user - the user object to update
     * @return - Deleted user
     */
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
        Optional<User> u = userService.getUser(id);
        if(u.isPresent()) {
            User currentUser = u.get();

            String firstName = user.getFirstName();
            if(firstName != null) {
                currentUser.setFirstName(firstName);
            }
            String lastName = user.getLastName();
            if(lastName != null) {
                currentUser.setLastName(lastName);
            }
            String mail = user.getMail();
            if(mail != null) {
                currentUser.setMail(mail);
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
