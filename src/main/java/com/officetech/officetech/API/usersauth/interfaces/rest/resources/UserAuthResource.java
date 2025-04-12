package com.officetech.officetech.API.usersauth.interfaces.rest.resources;

public record UserAuthResource(Long id, String firstName, String lastName, String email, String password, String role) {
}
