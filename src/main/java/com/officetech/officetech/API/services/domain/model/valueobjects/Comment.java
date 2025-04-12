package com.officetech.officetech.API.services.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Comment(String comment) {
    public Comment() {
        this("");
    }
}
