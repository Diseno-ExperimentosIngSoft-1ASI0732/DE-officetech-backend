package com.officetech.officetech.API.services.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Title(String title) {
    public Title(){this("");}
}
