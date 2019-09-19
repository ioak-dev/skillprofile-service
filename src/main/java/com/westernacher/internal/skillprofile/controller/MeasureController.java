package com.westernacher.internal.skillprofile.controller;

import com.westernacher.internal.skillprofile.domain.UnitOfMeasure;
import com.westernacher.internal.skillprofile.domain.User;
import com.westernacher.internal.skillprofile.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/measure")
public class MeasureController {

    @Autowired
    private MeasureRepository repository;

    @GetMapping
    public List<UnitOfMeasure> getAll () {
        return this.repository.findAll();
    }

    @PostMapping
    public UnitOfMeasure save(@RequestBody UnitOfMeasure unitOfMeasure) {
        return this.repository.save(unitOfMeasure);
    }

}
