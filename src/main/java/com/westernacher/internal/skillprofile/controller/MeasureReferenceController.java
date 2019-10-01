package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.Measure;
import com.westernacher.internal.skillprofile.domain.MeasureReference;
import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.MeasureReferenceRepository;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import com.westernacher.internal.skillprofile.representation.MeasureReferenceResource;
import com.westernacher.internal.skillprofile.representation.MeasureRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/measure")
public class MeasureReferenceController {

    @Autowired
    private MeasureReferenceRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<MeasureReference> getAll () {
        return this.repository.findAll();
    }

    @GetMapping(value = "/csvdata")
    public StringBuffer getCsvData () {
        List<User> userList = userRepository.findAll();
        List<String>                basicProfileNames         = new ArrayList<>();
        List<MeasureRepresentation> measureRepresentationList = new ArrayList<>();
        List<MeasureReference> measureReferenceList = repository.findAll();

        /*Header Part*/
        StringBuffer csvString= new StringBuffer();
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

        csvString.append(",,,,,,,,,,,,,,,");
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

    @PostMapping
    public List<MeasureReference> save(@RequestParam String type,
                                       @RequestBody List<MeasureReferenceResource> resourceList) {

        if (type == null || !(type.equalsIgnoreCase("ADD") ||
                              type.equalsIgnoreCase("DELETE") ||
                              type.equalsIgnoreCase("RENAME"))) {
            return null;
        }

        if (type.equalsIgnoreCase("ADD")) {
            add(resourceList);
        } else if (type.equalsIgnoreCase("DELETE")) {
            delete(resourceList);
        } else if (type.equalsIgnoreCase("RENAME")) {
            rename(resourceList);
        }

        return this.repository.findAll();
    }

    private void add(List<MeasureReferenceResource> resourceList) {
        List<User> userList = this.userRepository.findAll();
        List<Measure> measureList = new ArrayList<>();
        resourceList.stream().forEach(
            resource -> {
                this.repository.save(resource.toMeasureReference());
                measureList.add(new Measure(resource.toMeasureReference()));
            }
        );

        userList.stream().forEach(user -> {
            List<Measure> measures = user.getMeasures();
            measures.addAll(measureList);
            user.setMeasures(measures);
            this.userRepository.save(user);
        });
    }

    private void delete(List<MeasureReferenceResource> resourceList) {
        List<User> userList = this.userRepository.findAll();
        Map<String, List<String>> measureMap = new HashMap<>();
        resourceList.stream().forEach(
                resource -> {
                    this.repository.deleteByCategoryEqualsAndTopicEquals(resource.getCategory(), resource.getTopic());
                    if (measureMap.containsKey(resource.getCategory())) {
                        measureMap.get(resource.getCategory()).add(resource.getTopic());
                    } else {
                        List<String> topics = new ArrayList<>();
                        topics.add(resource.getTopic());
                        measureMap.put(resource.getCategory(), topics);
                    }
                }
        );

        userList.stream().forEach(user -> {
            List<Measure> userMeasures = user.getMeasures();

            List<Measure> filteredUserMeasures = new ArrayList<>();

            userMeasures.stream().forEach(
                    userMeasure -> {
                        if (!(measureMap.containsKey(userMeasure.getCategory()) &&
                            measureMap.get(userMeasure.getCategory()).contains(userMeasure.getTopic()))) {
                            filteredUserMeasures.add(userMeasure);
                        }
                    }
            );

            user.setMeasures(filteredUserMeasures);
            this.userRepository.save(user);
        });
    }

    private void rename(List<MeasureReferenceResource> resourceList) {
        List<User> userList = this.userRepository.findAll();
        Map<String, Map<String, Measure>> measureMap = new HashMap<>();
        resourceList.stream().forEach(
            resource -> {
                MeasureReference reference = this.repository.findFirstByCategoryEqualsAndTopicEquals(resource.getCategory(),
                                                                                                     resource.getTopic());
                reference.setCategory(resource.getNewCategory());
                reference.setTopic(resource.getNewTopic());
                this.repository.save(reference);
                if (measureMap.containsKey(resource.getCategory())) {
                    measureMap.get(resource.getCategory()).put(resource.getTopic(), new Measure(resource.toMeasureReference()));
                } else {
                    Map<String, Measure> map = new HashMap<>();
                    map.put(resource.getTopic(), new Measure(resource.toMeasureReference()));
                    measureMap.put(resource.getCategory(), map);
                }
            }
        );

        userList.stream().forEach(user -> {
            List<Measure> userMeasures = user.getMeasures();

            List<Measure> filteredUserMeasures = new ArrayList<>();

            userMeasures.stream().forEach(
                userMeasure -> {
                    if (measureMap.containsKey(userMeasure.getCategory()) &&
                          measureMap.get(userMeasure.getCategory()).containsKey(userMeasure.getTopic())) {
                        Measure measure = measureMap.get(userMeasure.getCategory()).get(userMeasure.getTopic());
                        measure.setUnitofMeasure(userMeasure.getUnitofMeasure());
                        filteredUserMeasures.add(measure);
                    } else {
                        filteredUserMeasures.add(userMeasure);
                    }
                }
            );

            user.setMeasures(filteredUserMeasures);
            this.userRepository.save(user);
        });
    }
}
