package com.westernacher.internal.skillprofile.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "userprofile")
public class UserProfile {
    @Id
    private String id;
    private String userId;
    private List<UnitOfMeasure> profiles;
}
