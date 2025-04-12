package com.officetech.officetech.API.usersauth.interfaces.rest.resources;

import com.officetech.officetech.API.usersauth.domain.model.commands.CreateUserAuthCommand;

import java.util.List;

public class ValidateUserAuthCommand {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    private final String role;


    public ValidateUserAuthCommand(CreateUserAuthCommand command) {
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.password = command.password();
        this.role = command.role();
    }

    public String validate() {
        if (firstName == null || firstName.isBlank() || !firstName.matches("^[a-zA-Z]*$")) {
            return "First name cannot be null or empty and must contain only letters";
        }
        if (lastName == null || lastName.isBlank() || !lastName.matches("^[a-zA-Z]*$")) {
            return "Last name cannot be null or empty and must contain only letters";
        }

        // regex to validate email
        if (email == null || email.isBlank() || !email.matches("^(.+)@(.+)$")) {
            return "Email is not valid, make sure it is not null or empty and contains @ symbol";
        }
        if (password == null || password.isBlank()) {
            return "Password cannot be null or empty";
        }
        if (role == null || role.isBlank()) {
            return "Role cannot be null or empty";
        }
        return "";
    }
}
