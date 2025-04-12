package com.officetech.officetech.API.payment.interfaces.rest.resources;

public record PaymentDetailsResource(Long user_id, String cardHolderName, String cardNumber, String expiratoryMonth, String expiratoryYear, String cvv){
}
