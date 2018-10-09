package com.sharegoods.inth3rship.restcontrollers;

import com.sharegoods.inth3rship.dao.UserDAO;
import com.sharegoods.inth3rship.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public User getUserByLoginData(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password)
    {
        return UserDAO.getUserByLoginData(email, password);
    }
}
