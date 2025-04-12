package com.officetech.officetech.API.usersauth.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable // this annotation is to indicate that the class is an embed, embed is a value object that is part of an entity
public record Email(String email) {
    public Email() {this(null);}
    public Email {
        String regex = "^(.+)@(.+)$";
        if(!email.matches(regex)) {
            System.out.println("Email is not valid");

        }
        if (email == null || email.isBlank()) {
            System.out.println("Email cannot be null or blank");
        }
    }

    public String getEmail() {return email;}
}
