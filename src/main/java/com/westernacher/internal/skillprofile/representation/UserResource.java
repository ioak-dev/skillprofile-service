package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResource {
    protected final String id;
    protected final String email;
    protected final String firstName;
    protected final String lastName;
    protected final String fullName;

    public static UserResource getUserResource(User user) {
        return UserResource.builder()
                           .email(user.getEmail())
                           .fullName(user.getFirstName() + user.getLastName())
                           .build();
    }
}
