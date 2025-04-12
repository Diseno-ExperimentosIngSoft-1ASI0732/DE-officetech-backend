package com.officetech.officetech.API.payment.domain.model.aggregates;

import com.officetech.officetech.API.payment.domain.model.commands.CreatePaymentDetailsCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PaymentDetail extends AbstractAggregateRoot<PaymentDetail> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private Long userId;

    @Column(nullable = false)
    @Getter
    private String cardHolderName;

    @Column(nullable = false)
    @Getter
    private String cardNumber;

    @Column
    @Getter
    private String expiratoryMonth;

    @Column(nullable = false)
    @Getter
    private String expiratoryYear;

    @Column(nullable = false)
    @Getter
    private String cvv;

    protected PaymentDetail() {}

    public PaymentDetail(CreatePaymentDetailsCommand command) {
        this.userId = command.userId();
        this.cardHolderName = command.cardHolderName();
        this.cardNumber = command.cardNumber();
        this.expiratoryMonth = command.expiratoryMonth();
        this.expiratoryYear = command.expiratoryYear();
        this.cvv = command.cvv();
    }
    /**
     * It creates a new payment details instance
     * @Param user_id the identifier of the user
     */


}
