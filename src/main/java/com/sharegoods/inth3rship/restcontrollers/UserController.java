package com.sharegoods.inth3rship.restcontrollers;

import com.sharegoods.inth3rship.services.UserService;
import com.sharegoods.inth3rship.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUSerById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> getUserByLoginData(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.getUserByLoginData(email, password);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User newUser) {
        return userService.createNewUser(newUser);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
       userService.deleteUser(id);
    }
}
