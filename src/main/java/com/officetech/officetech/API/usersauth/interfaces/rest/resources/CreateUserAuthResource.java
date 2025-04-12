package com.officetech.officetech.API.usersauth.interfaces.rest.resources;

public record CreateUserAuthResource(String firstName, String lastName, String email, String password, String role) {
}
