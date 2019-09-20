package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.UnitofMeasure;
import com.westernacher.internal.skillprofile.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class BasicProfile extends UserResource{
    private final String empId;
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

    public static User toUser(BasicProfile basicProfile, User user) {
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
