package com.westernacher.internal.skillprofile.representation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResource {
    protected final String id;
    protected final String email;
    protected final String firstName;
    protected final String lastName;
}
