package com.officetech.officetech.API.payment.application.internal.commandservices;

import com.officetech.officetech.API.payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.payment.domain.model.commands.CreatePaymentDetailsCommand;
import com.officetech.officetech.API.payment.domain.services.PaymentDetailsCommandService;
import com.officetech.officetech.API.payment.infrastructure.persistence.jpa.repositories.PaymentDetailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentDetailsCommandServiceImpl implements PaymentDetailsCommandService {

    private final PaymentDetailRepository paymentDetailRepository;

    public PaymentDetailsCommandServiceImpl(PaymentDetailRepository paymentDetailRepository) {
        this.paymentDetailRepository = paymentDetailRepository;
    }

    @Override
    public Optional<PaymentDetail> handle(CreatePaymentDetailsCommand command){
        if(paymentDetailRepository.existsById(command.userId())){
            throw new IllegalArgumentException("payment with same user ID already exists");
        }
        PaymentDetail paymentDetail = new PaymentDetail(command);
        paymentDetailRepository.save(paymentDetail);
        return Optional.of(paymentDetail);
    }
}
