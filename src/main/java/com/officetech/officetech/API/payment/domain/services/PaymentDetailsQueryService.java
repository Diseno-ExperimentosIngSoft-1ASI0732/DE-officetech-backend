package com.officetech.officetech.API.payment.domain.services;

import com.officetech.officetech.API.payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.payment.domain.model.queries.GetPaymentDetailByUserId;

import java.util.Optional;

public interface PaymentDetailsQueryService {
    Optional<PaymentDetail> hande(GetPaymentDetailByUserId query);

    Optional<PaymentDetail> findByUserId(Long userId);
}
