package com.officetech.officetech.API.usersauth.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FirstName(String firstName) {
    public FirstName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (firstName.length() > 50){
            throw new IllegalArgumentException("First name must be less than 50 characters");
        }
        if(!firstName.matches("^[a-zA-Z]*$") ){
            throw new IllegalArgumentException("First name must contain only letters");
        }
    }
    public String getFirstName() {
        return firstName;
    }
}
