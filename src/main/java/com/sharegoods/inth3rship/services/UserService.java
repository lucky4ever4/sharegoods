package com.sharegoods.inth3rship.services;

import com.sharegoods.inth3rship.models.User;
import com.sharegoods.inth3rship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id)
    {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }
}
