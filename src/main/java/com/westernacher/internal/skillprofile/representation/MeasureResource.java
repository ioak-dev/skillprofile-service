package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.Measure;
import com.westernacher.internal.skillprofile.domain.UnitofMeasure;
import lombok.Getter;

@Getter
public class MeasureResource {

    public MeasureResource(Measure measure) {
        this.category = measure.getCategory();
        this.topic = measure.getTopic();
        this.years = measure.getUnitofMeasure().getYears();
        this.months = measure.getUnitofMeasure().getMonths();
    }

    public Measure toMeasure() {
        Measure measure = new Measure();

        measure.setCategory(this.category);
        measure.setTopic(this.topic);
        measure.setUnitofMeasure(new UnitofMeasure(this.years, this.months));

        return measure;
    }

    private final String category;
    private final String topic;
    private final int years;
    private final int months;
}
