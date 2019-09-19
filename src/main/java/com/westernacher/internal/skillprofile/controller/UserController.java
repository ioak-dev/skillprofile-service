package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import com.westernacher.internal.skillprofile.representation.AdvancedProfile;
import com.westernacher.internal.skillprofile.representation.BasicProfile;
import com.westernacher.internal.skillprofile.representation.MeasureResource;
import com.westernacher.internal.skillprofile.representation.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAll () {
        return this.repository.findAll();
    }

    @PutMapping(value = "/{userId}/profilebasic")
    public BasicProfile saveBasicProfile(@RequestBody BasicProfile basicProfile) {
        //        return UserResource.getUserResource(this.repository.save(user));
    }

    @PutMapping(value = "/{userId}/profileadvanced")
    public AdvancedProfile saveAdvancedProfile(@RequestBody AdvancedProfile advancedProfile) {
        //        return UserResource.getUserResource(this.repository.save(user));
    }

    @GetMapping(value = "/email/{emailId}")
    public UserResource getUserByEmailId(@PathVariable String emailId) {
        return UserResource.getUserResource(this.repository.getByEmail(emailId));
    }

    @GetMapping(value = "/{userId}/profileBasic")
    public BasicProfile getBasicProfile(@PathVariable String userId) {
        User user = this.repository.getById(userId);

        return BasicProfile.builder()
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

    }

    @GetMapping(value = "/{userId}/profileAdvanced")
    public AdvancedProfile getAdvancedProfile(@PathVariable String userId) {
        User user = this.repository.getById(userId);

        AdvancedProfile advancedProfile = new AdvancedProfile();
        user.getMeasures().stream().forEach((measure) -> {
            advancedProfile.addunitOfMeasure(new MeasureResource(measure));
        });

        return advancedProfile;
    }
}
