package com.westernacher.internal.skillprofile.repository;

import com.westernacher.internal.skillprofile.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
