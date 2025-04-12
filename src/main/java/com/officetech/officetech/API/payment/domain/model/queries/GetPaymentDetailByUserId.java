package com.officetech.officetech.API.payment.domain.model.queries;

public record GetPaymentDetailByUserId(Long userId) {
    public GetPaymentDetailByUserId {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
    }

    public Long user_id() {
        return userId;
    }
}
