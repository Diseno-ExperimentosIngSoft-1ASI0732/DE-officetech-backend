package com.officetech.officetech.API.services.domain.model.commands;

import com.officetech.officetech.API.services.domain.model.valueobjects.*;

public record CreateNewServiceOfficeTechCommand(String title, String description, Float estimatePricing, String date, String comment, Long companyId, Long technicianId, Integer rating) {
}
