package com.westernacher.internal.skillprofile.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "person")
public class User {

    @Id
    private String id;
    private String empId;
    private String name;
    private String email;
    private String unit;
    private Date joiningDate;
    private String designation;
    private Date lastAppraisalDate;
    private int duration;

}
