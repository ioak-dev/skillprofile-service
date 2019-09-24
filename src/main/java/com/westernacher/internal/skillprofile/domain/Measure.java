package com.westernacher.internal.skillprofile.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measure {
    private String category;
    private String topic;
    private UnitofMeasure unitofMeasure;
}
