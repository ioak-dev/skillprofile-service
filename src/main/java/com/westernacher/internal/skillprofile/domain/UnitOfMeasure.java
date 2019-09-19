package com.westernacher.internal.skillprofile.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "measure")
public class UnitOfMeasure {
    private String category;
    private String topic;
    private int years;
    private int months;

}
