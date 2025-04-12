package com.officetech.officetech.API.usersauth.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record LastName(String lastName) {
    public LastName {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (lastName.length() > 50){
            throw new IllegalArgumentException("Last name must be less than 50 characters");
        }
        if(!lastName.matches("^[a-zA-Z]*$") ){
            throw new IllegalArgumentException("Last name must contain only letters");
        }
    }
    public String getLastName() {
        return lastName;
    }
}
