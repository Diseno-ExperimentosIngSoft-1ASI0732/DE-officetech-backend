package com.officetech.officetech.API.services.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Status {
    IN_PROGRESS,
    ACTIVE,
    CANCELLED,
    COMPLETED
}
