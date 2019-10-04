package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.Measure;
import com.westernacher.internal.skillprofile.domain.MeasureReference;
import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.MeasureReferenceRepository;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import com.westernacher.internal.skillprofile.representation.MeasureRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private MeasureReferenceRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<MeasureReference> getAll () {
        return this.repository.findAll();
    }

    @GetMapping(value = "/csv")
    public StringBuffer getCsvData () {
        List<User> userList = this.userRepository.findAll();
        List<String>                basicProfileNames         = new ArrayList<>();
        List<MeasureRepresentation> measureRepresentationList = new ArrayList<>();
        List<MeasureReference> measureReferenceList = this.repository.findAll();

        /*Header Part*/
        StringBuffer csvString= new StringBuffer();
        csvString.append(",,,,,,,,,,,,,,,");

        measureReferenceList.stream().forEach(measureReference -> {
            measureRepresentationList.add(MeasureRepresentation.builder()
                                                               .category(measureReference.getCategory())
                                                               .topic(measureReference.getTopic())
                                                               .build());
        });
        List<String> oldHeader =  new ArrayList<>();
        for (MeasureReference measureReference:measureReferenceList) {
            if (!oldHeader.contains(measureReference.getCategory())){
                csvString.append(measureReference.getCategory());
                oldHeader.add(measureReference.getCategory());
                csvString.append(",");
            }else {
                csvString.append(",");
            }
        }

        csvString.append(System.lineSeparator());

        csvString.append("First Name");
        csvString.append(",");
        csvString.append("Last Name");
        csvString.append(",");
        csvString.append("Employee Id");
        csvString.append(",");
        csvString.append("Email");
        csvString.append(",");
        csvString.append("Designation");
        csvString.append(",");
        csvString.append("Primary Tech");
        csvString.append(",");
        csvString.append("Primary Skill");
        csvString.append(",");
        csvString.append("Billability");
        csvString.append(",");
        csvString.append("careerStartDate");
        csvString.append(",");
        csvString.append("joiningDate");
        csvString.append(",");
        csvString.append("carrerGap");
        csvString.append(",");
        csvString.append("totalExp");
        csvString.append(",");
        csvString.append("functionalExp");
        csvString.append(",");
        csvString.append("previousWesternacherExp");
        csvString.append(",");
        csvString.append("totalWesternacherExp");
        csvString.append(",");
        measureReferenceList.stream().forEach(measureReference -> {
            csvString.append(measureReference.getTopic());
            csvString.append(",");
        });
        csvString.append(System.lineSeparator());

        /*Header Part*/


        /*Value Part*/

        for (User user:userList) {
            csvString.append(user.getFirstName());
            csvString.append(",");
            csvString.append(user.getLastName());
            csvString.append(",");
            csvString.append(user.getEmpId());
            csvString.append(",");
            csvString.append(user.getEmail());
            csvString.append(",");
            csvString.append(user.getDesignation());
            csvString.append(",");
            csvString.append(user.getPrimaryTech());
            csvString.append(",");
            csvString.append(user.getPrimarySkill());
            csvString.append(",");
            csvString.append(user.isBillability());
            csvString.append(",");
            csvString.append(user.getCareerStartDate());
            csvString.append(",");
            csvString.append(user.getJoiningDate());
            csvString.append(",");
            csvString.append(User.toString(user.getCarrerGap()));
            csvString.append(",");
            csvString.append(User.toString(user.getTotalExp()));
            csvString.append(",");
            csvString.append(User.toString(user.getFunctionalExp()));
            csvString.append(",");
            csvString.append(User.toString(user.getPreviousWesternacherExp()));
            csvString.append(",");
            csvString.append(User.toString(user.getTotalWesternacherExp()));
            csvString.append(",");

            List<Measure> measureList = user.getMeasures();
            StringBuffer stringBuffer = new StringBuffer();

            measureList.stream().forEach(measure -> {
                stringBuffer.append(User.toString(measure.getUnitofMeasure()));
                stringBuffer.append(",");
            });
            csvString.append(stringBuffer);
            csvString.append(System.lineSeparator());
        }


        /*Value Part*/


        return csvString;
    }
}

