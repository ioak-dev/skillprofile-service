package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.UnitOfMeasure;
import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAll () {
        return repository.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping(value = "/{userId}/profile")
    public Map<String, List<UnitOfMeasure>> getUserProfileMapByUserId(@PathVariable String userId) {
        User                user     = repository.getById(userId);
        Map<String, List<UnitOfMeasure>> userMap = new HashMap<>();

        user.getProfiles().stream().forEach((profile) -> {
            if (userMap.containsKey(profile.getCategory())) {
                userMap.get(profile.getCategory()).add(profile);
            } else {
                List<UnitOfMeasure> list = new ArrayList<>();
                list.add(profile);
                userMap.put(profile.getCategory(), list);
            }
        });

        return userMap;
    }

}
