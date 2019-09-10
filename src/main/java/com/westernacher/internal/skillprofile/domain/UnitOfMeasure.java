package com.westernacher.internal.skillprofile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnitOfMeasure {
    private String category;
    private String topic;
    private ExperienceType measure;

}
