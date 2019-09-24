package com.westernacher.internal.skillprofile.representation;

import lombok.*;

@Getter
@AllArgsConstructor
public class UserResource {
    protected String id;
    protected String empId;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String fullName;

    public String getEmail() {
        return this.email != null ? this.email.toLowerCase() : null;
    }
}
