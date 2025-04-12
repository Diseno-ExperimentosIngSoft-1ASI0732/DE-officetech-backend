package com.officetech.officetech.API.profile.domain.model.commands;

public record UpdateProfileCommand(Long id, String firstName, String lastName, String phone, String password) {
    public UpdateProfileCommand {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (firstName == null) {
            throw new IllegalArgumentException("firstName is required");
        }
        if (lastName == null) {
            throw new IllegalArgumentException("lastName is required");
        }
        if (phone == null) {
            throw new IllegalArgumentException("phone is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
    }
}
