package com.officetech.officetech.API.usersauth.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Role(String role) {
    public Role(){this(null);}
    public Role {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }

    public String getRole() {
        return role;
    }
}
