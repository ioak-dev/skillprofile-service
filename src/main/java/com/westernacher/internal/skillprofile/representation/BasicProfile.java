package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class BasicProfile extends UserResource{
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

    @Builder
    public BasicProfile(String id, String empId, String email, String firstName, String lastName, String fullName, String designation,
                        String primaryTech, String primarySkill, boolean billability, Date careerStartDate, Date joiningDate,
                        Integer carrerGapYears, Integer carrerGapMonths, Integer totalExpYears, Integer totalExpMonths,
                        Integer functionalExpYears, Integer functionalExpMonths, Integer previousWesternacherExpYears,
                        Integer previousWesternacherExpMonths, Integer totalWesternacherExpYears, Integer totalWesternacherExpMonths) {
        super(id, empId, email, firstName, lastName, fullName);
        this.designation = designation;
        this.primaryTech = primaryTech;
        this.primarySkill = primarySkill;
        this.billability = billability;
        this.careerStartDate = careerStartDate;
        this.joiningDate = joiningDate;
        this.carrerGapYears = carrerGapYears;
        this.carrerGapMonths = carrerGapMonths;
        this.totalExpYears = totalExpYears;
        this.totalExpMonths = totalExpMonths;
        this.functionalExpYears = functionalExpYears;
        this.functionalExpMonths = functionalExpMonths;
        this.previousWesternacherExpYears = previousWesternacherExpYears;
        this.previousWesternacherExpMonths = previousWesternacherExpMonths;
        this.totalWesternacherExpYears = totalWesternacherExpYears;
        this.totalWesternacherExpMonths = totalWesternacherExpMonths;
    }

    public static BasicProfile toBasicProfile(User user) {
        if (user == null) {
            return null;
        }

        return BasicProfile.builder()
                           .id(user.getId())
                           .empId(user.getEmpId())
                           .email(user.getEmail())
                           .firstName(user.getFirstName())
                           .lastName(user.getLastName())
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
