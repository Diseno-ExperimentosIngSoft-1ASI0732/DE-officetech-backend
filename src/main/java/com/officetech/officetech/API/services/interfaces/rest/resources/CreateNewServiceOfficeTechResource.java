package com.officetech.officetech.API.services.interfaces.rest.resources;

public record CreateNewServiceOfficeTechResource(String title, String description, Float estimatePricing, String date, String comment, Long companyId, Long technicianId, Integer rating) {
}
