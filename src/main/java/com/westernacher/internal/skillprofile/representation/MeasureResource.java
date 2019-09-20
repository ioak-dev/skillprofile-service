package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.Measure;
import com.westernacher.internal.skillprofile.domain.UnitofMeasure;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MeasureResource {

    private final String category;
    private final String topic;
    private final int years;
    private final int months;

    public MeasureResource(Measure measure) {
        this.category = measure.getCategory();
        this.topic = measure.getTopic();
        this.years = measure.getUnitofMeasure().getYears();
        this.months = measure.getUnitofMeasure().getMonths();
    }

    public static Measure toMeasure(MeasureResource measureResource) {
        Measure measure = new Measure();

        measure.setCategory(measureResource.category);
        measure.setTopic(measureResource.topic);
        measure.setUnitofMeasure(new UnitofMeasure(measureResource.years, measureResource.months));

        return measure;
    }

    public static List<Measure> toListOfMeasure(List<MeasureResource> measureResources) {
        List<Measure> measures = new ArrayList<>();
        measureResources.stream().forEach(measureResource -> measures.add(new Measure(measureResource.category, measureResource.topic,
                                                                                      new UnitofMeasure(measureResource.years, measureResource.months))));
        return measures;
    }
}
