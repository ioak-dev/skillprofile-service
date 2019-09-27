package com.westernacher.internal.skillprofile.repository;

import com.westernacher.internal.skillprofile.domain.MeasureReference;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasureReferenceRepository extends MongoRepository<MeasureReference, String> {
    void deleteByCategoryEqualsAndTopicEquals(String category, String topic);
    MeasureReference findFirstByCategoryEqualsAndTopicEquals(String category, String topic);
}
