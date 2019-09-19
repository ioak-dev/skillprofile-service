package com.westernacher.internal.skillprofile.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Measure {
    private String category;
    private String topic;
    private UnitofMeasure unitofMeasure;
}
