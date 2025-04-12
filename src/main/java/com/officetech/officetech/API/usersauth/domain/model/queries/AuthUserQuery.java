package com.officetech.officetech.API.usersauth.domain.model.queries;

import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Email;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Password;

public record AuthUserQuery(Email email, Password password) {
}
