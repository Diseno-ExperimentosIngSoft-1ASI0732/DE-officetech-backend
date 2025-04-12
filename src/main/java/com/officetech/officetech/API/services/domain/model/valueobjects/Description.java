package com.officetech.officetech.API.services.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Description(String description) {
    public Description(){this("");}
}
