package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.helpers.hash.HashPassword;
import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    public ResponseEntity<User> getUserByLoginData(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            password = HashPassword.getPasswordHash(password.getBytes(), "SHA-512");
            if (password.equals(user.getPassword())) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public User createNewUser(User newUser) {
        newUser.setPassword(HashPassword.getPasswordHash(newUser.getPassword().getBytes(), "SHA-512"));
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, User user) {
        User userToUpdate = getUserById(id);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail((user.getEmail()));
        if (!user.getPassword().isEmpty()) {
            userToUpdate.setPassword(HashPassword.getPasswordHash(user.getPassword().getBytes(), "SHA-512"));
        }
        return userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
