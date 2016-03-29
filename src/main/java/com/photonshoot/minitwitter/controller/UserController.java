package com.photonshoot.minitwitter.controller;

import com.photonshoot.minitwitter.domain.Users;
import com.photonshoot.minitwitter.model.User;
import com.photonshoot.minitwitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody Users list() {
        List<User> users = userRepository.list();
        return new Users(users);
    }
}
