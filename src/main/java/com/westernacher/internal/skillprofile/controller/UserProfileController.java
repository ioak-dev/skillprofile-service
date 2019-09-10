package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.UnitOfMeasure;
import com.westernacher.internal.skillprofile.domain.UserProfile;
import com.westernacher.internal.skillprofile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    @Autowired
    private UserProfileRepository repository;

    @GetMapping
    public List<UserProfile> getAll () {
         return repository.findAll();
    }

    @PostMapping
    public UserProfile save(@RequestBody UserProfile user) {
        return repository.save(user);
    }


    @GetMapping(value = "/{userId}")
    public Map<String, List<UnitOfMeasure>> getUserProfileMapByUserId(@PathVariable String userId) {
        List<UnitOfMeasure> unitOfMeasures = repository.getAllByUserId(userId);
        Map<String, List<UnitOfMeasure>> unitOfMeasureMap = new HashMap<>();

        unitOfMeasures.stream().forEach(unitOfMeasure -> {
            unitOfMeasureMap.put(unitOfMeasure.getCategory(),unitOfMeasures);
        });
        return unitOfMeasureMap;
    }

}
