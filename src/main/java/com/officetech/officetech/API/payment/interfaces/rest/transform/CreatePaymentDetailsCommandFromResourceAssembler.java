package com.officetech.officetech.API.payment.interfaces.rest.transform;

import com.officetech.officetech.API.payment.domain.model.commands.CreatePaymentDetailsCommand;
import com.officetech.officetech.API.payment.interfaces.rest.resources.CreatePaymentDetailsResource;

public class CreatePaymentDetailsCommandFromResourceAssembler {
    public static CreatePaymentDetailsCommand toCommandFromResource(CreatePaymentDetailsResource resource) {
        System.out.println("CreatePaymentDetailsCommandFromResourceAssembler: converting resource to command");
        return new CreatePaymentDetailsCommand(resource.user_id(), resource.cardHolderName(), resource.cardNumber(), resource.expiratoryMonth(), resource.expiratoryYear(), resource.cvv());
    }
}
