package com.officetech.officetech.API.payment.interfaces.rest.resources;

public record CreatePaymentDetailsResource(Long user_id, String cardHolderName, String cardNumber, String expiratoryMonth, String expiratoryYear, String cvv) {
    public CreatePaymentDetailsResource {
        if (user_id == null) {
            throw new IllegalArgumentException("user_id is required");
        }
        if (cardHolderName == null) {
            throw new IllegalArgumentException("cardHolderName is required");
        }
        if (cardNumber == null) {
            throw new IllegalArgumentException("cardNumber is required");
        }
        if(expiratoryMonth == null) {
            throw new IllegalArgumentException("expiratoryMonth is required");
        }
        if(expiratoryYear == null) {
            throw new IllegalArgumentException("expiratoryYear is required");
        }
        if(cvv == null) {
            throw new IllegalArgumentException("cvv is required");
        }
    }
}
