package com.officetech.officetech.API.payment.domain.model.commands;

/**
 * Command to create a new payment details
 * @param user_id the identifier of the user
 * @throws IllegalArgumentException if user_id is null or blank
 */
public record CreatePaymentDetailsCommand(Long userId, String cardHolderName, String cardNumber, String expiratoryMonth, String expiratoryYear, String cvv) {

    public CreatePaymentDetailsCommand {
        if (userId == null) {
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
