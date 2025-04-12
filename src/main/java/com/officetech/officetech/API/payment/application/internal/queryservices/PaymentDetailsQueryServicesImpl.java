package com.officetech.officetech.API.payment.application.internal.queryservices;

import com.officetech.officetech.API.payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.payment.domain.model.queries.GetPaymentDetailByUserId;
import com.officetech.officetech.API.payment.domain.services.PaymentDetailsQueryService;
import com.officetech.officetech.API.payment.infrastructure.persistence.jpa.repositories.PaymentDetailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentDetailsQueryServicesImpl implements PaymentDetailsQueryService{

    private final PaymentDetailRepository paymentDetailRepository;

    public PaymentDetailsQueryServicesImpl(PaymentDetailRepository paymentDetailRepository) {
        this.paymentDetailRepository = paymentDetailRepository;
    }

    /**
     * @param query - The query to get the payment details by user ID
     * @return an optional of the payment details
     */

    @Override
    public Optional<PaymentDetail> hande(GetPaymentDetailByUserId query){
        return paymentDetailRepository.findByUserId(query.user_id());
    }

    @Override
    public Optional<PaymentDetail> findByUserId(Long userId) {
        return paymentDetailRepository.findByUserId(userId);
    }

}
