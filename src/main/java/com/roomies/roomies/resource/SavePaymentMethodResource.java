package com.roomies.roomies.resource;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SavePaymentMethodResource {

    @NotNull
    private Long cardNumber;
    @NotNull
    private String cvv;

    @NotNull
    private Date expiryDate;

    public Long getCardNumber() {
        return cardNumber;
    }

    public SavePaymentMethodResource setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public SavePaymentMethodResource setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public SavePaymentMethodResource setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }
}
