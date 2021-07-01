package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.PaymentMethod;
import com.roomies.roomies.domain.repository.PaymentMethodRepository;
import com.roomies.roomies.domain.repository.ProfileRepository;
import com.roomies.roomies.domain.service.PaymentMethodService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<PaymentMethod> getAllPaymentMethods(Pageable pageable) {
        return paymentMethodRepository.findAll(pageable);
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod updatePaymentMethod(Long paymentMethodId, PaymentMethod paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
        paymentMethod.setCvv(paymentMethodRequest.getCvv())
                .setExpiryDate(paymentMethodRequest.getExpiryDate());
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public ResponseEntity<?> deletePaymentMethod(Long paymentMethodId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
        paymentMethodRepository.delete(paymentMethod);
        return ResponseEntity.ok().build();
    }
    @Override
    public Page<PaymentMethod> getAllPaymentMethodsByProfileId(Long profileId, Pageable pageable) {
        return profileRepository.findById(profileId).map(
                user -> {List<PaymentMethod> paymentMethods = user.getProfilePaymentMethods();
                int paymentMethodsCount = paymentMethods.size();
                return new PageImpl<>(paymentMethods,pageable,paymentMethodsCount);}
                ).orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
    }
}
