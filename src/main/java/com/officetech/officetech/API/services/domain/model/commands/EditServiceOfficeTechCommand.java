package com.officetech.officetech.API.services.domain.model.commands;

public record EditServiceOfficeTechCommand(String comment, Integer rating, Long serviceId) {
}
