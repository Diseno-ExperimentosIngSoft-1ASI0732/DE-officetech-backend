package com.officetech.officetech.API.usersauth.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {
    public Password() { this(null); }
    public Password {
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be null or blank");
    }
    public String getPassword() {
        return password;
    }
}
