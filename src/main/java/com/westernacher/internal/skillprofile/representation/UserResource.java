package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {
    protected String id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String fullName;

    /*public static UserResource toUserResource(User user) {
        return UserResource.builder()
                           .id(user.getId())
                           .email(user.getEmail())
                           .firstName(user.getFirstName())
                           .lastName(user.getLastName())
                           .fullName(user.getFirstName() + user.getLastName())
                           .build();
    }*/
}
