package com.officetech.officetech.API.services.domain.model.valueobjects;

public record Pricing(Float pricing) {
    public Pricing(){this((float) 0);}
}
