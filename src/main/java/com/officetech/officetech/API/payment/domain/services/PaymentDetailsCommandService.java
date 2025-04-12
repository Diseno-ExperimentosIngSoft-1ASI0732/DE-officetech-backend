package com.officetech.officetech.API.payment.domain.services;

import com.officetech.officetech.API.payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.payment.domain.model.commands.CreatePaymentDetailsCommand;

import java.util.Optional;

public interface PaymentDetailsCommandService {
    Optional<PaymentDetail> handle(CreatePaymentDetailsCommand command);
}
