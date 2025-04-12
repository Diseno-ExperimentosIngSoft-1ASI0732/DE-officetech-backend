package com.officetech.officetech.API.iam.infrastructure.hashing.bcrypt;

import com.officetech.officetech.API.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}