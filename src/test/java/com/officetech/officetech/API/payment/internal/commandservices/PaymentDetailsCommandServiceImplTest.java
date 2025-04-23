package com.officetech.officetech.API.payment.internal.commandservices;

import com.officetech.officetech.API.payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.payment.domain.model.commands.CreatePaymentDetailsCommand;
import com.officetech.officetech.API.payment.infrastructure.persistence.jpa.repositories.PaymentDetailRepository;
import com.officetech.officetech.API.payment.application.internal.commandservices.PaymentDetailsCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentDetailsCommandServiceImplTest {

    private PaymentDetailRepository paymentDetailRepository;
    private PaymentDetailsCommandServiceImpl paymentDetailsCommandService;

    @BeforeEach
    void setup() {
        paymentDetailRepository = mock(PaymentDetailRepository.class);
        paymentDetailsCommandService = new PaymentDetailsCommandServiceImpl(paymentDetailRepository);
    }

    @Test
    void handle_WhenPaymentExists_ThrowsException() {
        // Arrange
        var userId = 1L;
        var command = new CreatePaymentDetailsCommand(userId, "John Doe", "1234567890123456", "12/25", "12/25","123");

        when(paymentDetailRepository.existsById(userId)).thenReturn(true);

        // Act & Assert
        var exception = assertThrows(IllegalArgumentException.class,
                () -> paymentDetailsCommandService.handle(command));
        assertEquals("payment with same user ID already exists", exception.getMessage());

        verify(paymentDetailRepository).existsById(userId);
        verifyNoMoreInteractions(paymentDetailRepository);
    }

    @Test
    void handle_WhenPaymentDoesNotExist_SavesPaymentDetail() {
        // Arrange
        var userId = 2L;
        var command = new CreatePaymentDetailsCommand(userId, "Jane Doe", "9876543210987654", "01/26", "456", "123");
        var paymentDetail = new PaymentDetail(command);

        when(paymentDetailRepository.existsById(userId)).thenReturn(false);
        when(paymentDetailRepository.save(any(PaymentDetail.class))).thenReturn(paymentDetail);

        // Act
        var result = paymentDetailsCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getUserId());
        assertEquals("9876543210987654", result.get().getCardNumber());

        verify(paymentDetailRepository).existsById(userId);
        verify(paymentDetailRepository).save(any(PaymentDetail.class));
    }
}