package com.officetech.officetech.API.usersauth.domain.model.commands;
/*
* Command to create a UserAuth.
* <p>The CreateUserAuthCommand record encapsulates the data required to create a UserAuth.
* It validates that the email, password, and role are not null or empty.</p>
* */
public record CreateUserAuthCommand(String firstName, String lastName, String email, String password, String role) {

}
