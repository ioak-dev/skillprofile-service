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
    public BasicProfile saveBasicProfile(@PathVariable String userId,
                                         @RequestBody BasicProfile basicProfile) {
        User user = repository.findById(userId).orElseGet(null);
        if (user!=null) {
            user = BasicProfile.toUser(basicProfile, user);
            return BasicProfile.toBasicProfile(repository.save(user));
        } else {
            user = BasicProfile.toUser(basicProfile, new User());
            return BasicProfile.toBasicProfile(repository.save(user));
        }

    }

    @PutMapping(value = "/{userId}/profileadvanced")
    public void saveAdvancedProfile(@PathVariable String userId,
                                               @RequestBody AdvancedProfile advancedProfile) {
        User user = repository.findById(userId).orElseGet(null);
        if (user!=null) {
            user = AdvancedProfile.toUser(advancedProfile, user);
            repository.save(user);
        } else {
            user = AdvancedProfile.toUser(advancedProfile, new User());
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
}
