package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.UnitofMeasure;
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
    public BasicProfile saveBasicProfile(@PathVariable String userId,
                                         @RequestBody BasicProfile basicProfile) {
        User user = repository.findById(userId).orElseGet(null);
        if (user!=null) {
            user = basicProfiletoUser(basicProfile, user);
            return BasicProfile.toBasicProfile(repository.save(user));
        } else {
            user = basicProfiletoUser(basicProfile, new User());
            return BasicProfile.toBasicProfile(repository.save(user));
        }

    }

    @PutMapping(value = "/{userId}/profileadvanced")
    public void saveAdvancedProfile(@PathVariable String userId,
                                               @RequestBody AdvancedProfile advancedProfile) {
        User user = repository.findById(userId).orElseGet(null);
        if (user!=null) {
            user = advancedProfiletoUser(advancedProfile, user);
            repository.save(user);
        } else {
            user = advancedProfiletoUser(advancedProfile, new User());
            repository.save(user);
        }
    }

    @GetMapping(value = "/email/{emailId}")
    public UserResource getUserByEmailId(@PathVariable String emailId) {
        return UserResource.getUserResource(this.repository.getByEmail(emailId));
    }

    @GetMapping(value = "/{userId}/profileBasic")
    public BasicProfile getBasicProfile(@PathVariable String userId) {
        User user = this.repository.getById(userId);
        return BasicProfile.toBasicProfile(user);
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

    private User advancedProfiletoUser(AdvancedProfile advancedProfile, User user) {
        advancedProfile.getData().forEach((k,v)->{
            user.getMeasures().addAll(MeasureResource.toListOfMeasure(v));
        });
        return user;
    }

    private User basicProfiletoUser(BasicProfile basicProfile, User user) {
        user.setFirstName(basicProfile.getFirstName());
        user.setLastName(basicProfile.getLastName());
        user.setEmpId(basicProfile.getEmpId());
        user.setEmail(basicProfile.getEmail());
        user.setDesignation(basicProfile.getDesignation());
        user.setPrimaryTech(basicProfile.getPrimaryTech());
        user.setPrimarySkill(basicProfile.getPrimarySkill());
        user.setBillability(basicProfile.isBillability());
        user.setMeasures(null);
        user.setCareerStartDate(basicProfile.getCareerStartDate());
        user.setJoiningDate(basicProfile.getJoiningDate());
        user.setCarrerGap(new UnitofMeasure(basicProfile.getCarrerGapYears(), basicProfile.getCarrerGapMonths()));
        user.setTotalExp(new UnitofMeasure(basicProfile.getTotalExpYears(), basicProfile.getTotalExpMonths()));
        user.setFunctionalExp(new UnitofMeasure(basicProfile.getFunctionalExpYears(), basicProfile.getFunctionalExpMonths()));
        user.setPreviousWesternacherExp(new UnitofMeasure(basicProfile.getPreviousWesternacherExpYears(), basicProfile.getPreviousWesternacherExpMonths()));
        user.setTotalWesternacherExp(new UnitofMeasure(basicProfile.getTotalWesternacherExpYears(), basicProfile.getTotalWesternacherExpMonths()));
        return user;
    }
}
