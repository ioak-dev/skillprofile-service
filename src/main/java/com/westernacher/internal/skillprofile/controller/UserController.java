package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.UnitofMeasure;
import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.MeasureReferenceRepository;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import com.westernacher.internal.skillprofile.representation.AdvancedProfile;
import com.westernacher.internal.skillprofile.representation.BasicProfile;
import com.westernacher.internal.skillprofile.representation.MeasureResource;
import com.westernacher.internal.skillprofile.representation.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MeasureReferenceRepository measureReferenceRepository;

    @GetMapping
    public List<User> getAll () {
        return this.repository.findAll();
    }

    @PutMapping(value = "/profileBasic")
    public BasicProfile save(@RequestBody BasicProfile basicProfile) {
        return persistBasicProfile(basicProfile);
    }

    private BasicProfile persistBasicProfile(BasicProfile basicProfile) {
        if (basicProfile.getEmail() == null) {
            return null;
        }

        return BasicProfile.toBasicProfile(this.repository.save(basicProfiletoUser(basicProfile, this.repository.getByEmail(basicProfile.getEmail()))));
    }

    @PostMapping(value = "/profileBasic")
    public List<BasicProfile> save(@RequestBody List<BasicProfile> basicProfileList) {

        List<BasicProfile> savedBasicProfileList = new ArrayList<>();

        for (BasicProfile basicProfile : basicProfileList) {
            savedBasicProfileList.add(persistBasicProfile(basicProfile));
        }

        return savedBasicProfileList;

    }

    @PutMapping(value = "/{userId}/profileAdvanced")
    public AdvancedProfile saveAdvancedProfile(@PathVariable String userId,
                                               @RequestBody AdvancedProfile advancedProfile) {
        User user = this.repository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        user = advancedProfiletoUser(advancedProfile, user);
        return AdvancedProfile.toAdvancedProfile(this.repository.save(user));
    }

    @GetMapping(value = "/email/{emailId}")
    public UserResource getUserByEmailId(@PathVariable String emailId) {
        if (emailId == null) {
            return null;
        }
        User user = this.repository.getByEmail(emailId.toLowerCase());
        return new UserResource(user.getId(), user.getEmpId(), user.getEmail(), user.getFirstName(), user.getLastName(),
                                user.getFirstName() +" "+ user.getLastName());
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
            user.setMeasures(MeasureResource.toListOfMeasure(v));
        });
        return user;
    }

    private User basicProfiletoUser(BasicProfile basicProfile, User user) {
        if (user == null) {
            user = new User(this.measureReferenceRepository.findAll());
        }
        user.setFirstName(basicProfile.getFirstName());
        user.setLastName(basicProfile.getLastName());
        user.setEmpId(basicProfile.getEmpId());
        user.setEmail(basicProfile.getEmail());
        user.setDesignation(basicProfile.getDesignation());
        user.setPrimaryTech(basicProfile.getPrimaryTech());
        user.setPrimarySkill(basicProfile.getPrimarySkill());
        user.setBillability(basicProfile.isBillability());
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
