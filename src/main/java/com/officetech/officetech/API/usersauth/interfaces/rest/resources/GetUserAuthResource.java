package com.officetech.officetech.API.usersauth.interfaces.rest.resources;

public record GetUserAuthResource(Integer status_code, String message, UserAuthResource user) {
}
