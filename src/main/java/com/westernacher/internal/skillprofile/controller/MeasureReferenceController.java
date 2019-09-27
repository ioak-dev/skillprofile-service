package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.Measure;
import com.westernacher.internal.skillprofile.domain.MeasureReference;
import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.MeasureReferenceRepository;
import com.westernacher.internal.skillprofile.repository.UserRepository;
import com.westernacher.internal.skillprofile.representation.MeasureReferenceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
