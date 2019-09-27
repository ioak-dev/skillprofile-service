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

    public Measure(MeasureReference reference) {
        this.category = reference.getCategory();
        this.topic = reference.getTopic();
        this.unitofMeasure = new UnitofMeasure(0, 0);
    }
}
