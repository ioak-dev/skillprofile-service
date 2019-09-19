package com.westernacher.internal.skillprofile.repository;

import com.westernacher.internal.skillprofile.domain.UnitOfMeasure;
import com.westernacher.internal.skillprofile.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasureRepository extends MongoRepository<UnitOfMeasure, String> {
}
