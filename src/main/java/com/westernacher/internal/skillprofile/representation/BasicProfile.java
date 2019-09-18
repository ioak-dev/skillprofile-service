package com.westernacher.internal.skillprofile.representation;

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
}
