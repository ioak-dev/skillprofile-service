package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.UnitOfMeasure;
import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import com.westernacher.internal.skillprofile.representation.BasicProfile;
import com.westernacher.internal.skillprofile.representation.UserResource;
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
        return this.repository.findAll();
    }

    @GetMapping(value = "/email/{emailId}")
    public UserResource getUserByEmailId(@PathVariable String emailId) {
        return UserResource.getUserResource(this.repository.getByEmail(emailId));
    }

    @PostMapping
    public UserResource save(@RequestBody User user) {
        return UserResource.getUserResource(this.repository.save(user));
    }

    @GetMapping(value = "/{userId}/profile")
    public Map getUserProfileMapByUserId(@PathVariable String userId) {
        User                user     = this.repository.getById(userId);
        Map<String, List<UnitOfMeasure>> measureCategories = new HashMap<>();

        Map responseMap = new HashMap();

        user.getMeasures().stream().forEach((profile) -> {
            if (measureCategories.containsKey(profile.getCategory())) {
                measureCategories.get(profile.getCategory()).add(profile);
            } else {
                List<UnitOfMeasure> list = new ArrayList<>();
                list.add(profile);
                measureCategories.put(profile.getCategory(), list);
            }
        });

        BasicProfile basic = BasicProfile.builder()
                                         .empId(user.getEmpId())
                                         .designation(user.getDesignation())
                                         .primaryTech(user.getPrimaryTech())
                                         .primarySkill(user.getPrimarySkill())
                                         .careerStartDate(user.getCareerStartDate())
                                         .joiningDate(user.getJoiningDate())
                                         .carrerGapYears(user.getCarrerGap().getYears())
                                         .carrerGapMonths(user.getCarrerGap().getMonths())
                                         .totalExpYears(user.getTotalExp().getYears())
                                         .totalExpMonths(user.getTotalExp().getMonths())
                                         .functionalExpYears(user.getFunctionalExp().getYears())
                                         .functionalExpMonths(user.getFunctionalExp().getMonths())
                                         .previousWesternacherExpYears(user.getPreviousWesternacherExp().getYears())
                                         .previousWesternacherExpMonths(user.getPreviousWesternacherExp().getMonths())
                                         .totalWesternacherExpYears(user.getTotalWesternacherExp().getYears())
                                         .totalWesternacherExpMonths(user.getTotalWesternacherExp().getMonths())
                                         .build();

        responseMap.put("profile", basic);
        responseMap.put("measures", measureCategories);

        return responseMap;
    }

}
