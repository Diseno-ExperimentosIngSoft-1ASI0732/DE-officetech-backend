package com.officetech.officetech.API.usersauth.interfaces.rest.resources;

public record ValidateUserAuthResource(Integer status_code, String message, GetCreateUserAuthResource userInfo) {
}
