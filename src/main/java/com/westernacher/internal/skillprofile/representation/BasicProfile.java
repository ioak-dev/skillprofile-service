package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class BasicProfile extends UserResource{
    private String firstName;
    private String lastName;
    private String email;
    private String empId;
    private final String designation;
    private final String primaryTech;
    private final String primarySkill;
    private final boolean billability;
    private final Date careerStartDate;
    private final Date joiningDate;
    private final Integer carrerGapYears;
    private final Integer carrerGapMonths;
    private final Integer totalExpYears;
    private final Integer totalExpMonths;
    private final Integer functionalExpYears;
    private final Integer functionalExpMonths;
    private final Integer previousWesternacherExpYears;
    private final Integer previousWesternacherExpMonths;
    private final Integer totalWesternacherExpYears;
    private final Integer totalWesternacherExpMonths;


    public static BasicProfile toBasicProfile(User user) {
        return BasicProfile.builder()
                           .firstName(user.getFirstName())
                           .lastName(user.getLastName())
                           .email(user.getEmail())
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


}
