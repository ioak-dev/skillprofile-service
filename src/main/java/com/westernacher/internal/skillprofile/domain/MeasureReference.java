package com.westernacher.internal.skillprofile.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "measure")
public class MeasureReference {
    private String category;
    private String topic;
    private UnitofMeasure unitofMeasure;
}
