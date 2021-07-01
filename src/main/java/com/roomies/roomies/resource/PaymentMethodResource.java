package com.roomies.roomies.resource;

import com.roomies.roomies.domain.model.AuditModel;

import java.util.Date;

public class PaymentMethodResource extends AuditModel {
    private Long id;
    private Long cardNumber;
    private String cvv;
    private Date expiryDate;

    public Long getId() {
        return id;
    }

    public PaymentMethodResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public PaymentMethodResource setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public PaymentMethodResource setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public PaymentMethodResource setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }
}
