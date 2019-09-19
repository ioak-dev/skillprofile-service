package com.westernacher.internal.skillprofile.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String empId;
    private String email;
    private String designation;
    private String primaryTech;
    private String primarySkill;
    private boolean billability;
    private List<UnitOfMeasure> measures;
    private Date careerStartDate;
    private Date joiningDate;
    private ExperienceType carrerGap;
    private ExperienceType totalExp;
    private ExperienceType functionalExp;
    private ExperienceType previousWesternacherExp;
    private ExperienceType totalWesternacherExp;

}
