package com.sharegoods.inth3rship.restcontrollers;

import com.sharegoods.inth3rship.services.UserService;
import com.sharegoods.inth3rship.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUSerById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users/login")
    public User getUserByLoginData(@RequestParam("email") String email,
                                   @RequestParam("password") String password) {
        return userService.getUserByLoginData(email, password);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User newUser) {
        return userService.createNewUser(newUser);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(name="id") Long id) {
        return userService.updateUser(id, user);
    }
}
