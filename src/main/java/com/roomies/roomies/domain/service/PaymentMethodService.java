package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentMethodService {
    Page<PaymentMethod> getAllPaymentMethods(Pageable pageable);
    PaymentMethod getPaymentMethodById(Long paymentMethodId);
    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);
    PaymentMethod updatePaymentMethod(Long paymentMethodId,PaymentMethod paymentMethodRequest);
    ResponseEntity<?> deletePaymentMethod(Long paymentMethodId);
    Page<PaymentMethod> getAllPaymentMethodsByProfileId(Long paymentMethodId,Pageable pageable);
}
