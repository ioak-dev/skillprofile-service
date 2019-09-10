package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll () {
        return repository.findAll();
    }

}
