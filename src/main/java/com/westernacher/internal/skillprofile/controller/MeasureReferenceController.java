package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.MeasureReference;
import com.westernacher.internal.skillprofile.repository.MeasureReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/measure")
public class MeasureReferenceController {

    @Autowired
    private MeasureReferenceRepository repository;

    @GetMapping
    public List<MeasureReference> getAll () {
        return this.repository.findAll();
    }

    @PostMapping
    public MeasureReference save(@RequestBody MeasureReference measure) {
        return this.repository.save(measure);
    }

}
