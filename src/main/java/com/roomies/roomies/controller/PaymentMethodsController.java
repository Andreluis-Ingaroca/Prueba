package com.roomies.roomies.controller;

import com.roomies.roomies.domain.model.PaymentMethod;
import com.roomies.roomies.domain.model.Profile;
import com.roomies.roomies.domain.service.PaymentMethodService;
import com.roomies.roomies.domain.service.ProfileService;
import com.roomies.roomies.resource.PaymentMethodResource;
import com.roomies.roomies.resource.ProfileResource;
import com.roomies.roomies.resource.SavePaymentMethodResource;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentMethodsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private ProfileService profileService;


    @PostMapping("/methods")
    public PaymentMethodResource createPaymentMethod(@Valid @RequestBody SavePaymentMethodResource resource){
        return convertToResourcePaymentMethod(paymentMethodService.createPaymentMethod(convertToEntityPaymentMethod(resource)));
    }


    @PutMapping("/methods/{methodId}")
    public PaymentMethodResource updatePaymentMethod(@PathVariable Long methodId, @Valid @RequestBody SavePaymentMethodResource resource){
        return convertToResourcePaymentMethod(paymentMethodService.updatePaymentMethod(methodId,convertToEntityPaymentMethod(resource)));
    }


    @DeleteMapping("/methods/{methodId}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long methodId){
        return paymentMethodService.deletePaymentMethod(methodId);
    }

    @GetMapping("/methods/{methodId}/profiles")
    public Page<ProfileResource> getAllProfilesByPaymentMethodId(@PathVariable Long methodId, Pageable pageable){
        List<ProfileResource> users = profileService.getAllProfilesByPaymentMethodId(methodId,pageable)
                .getContent().stream().map(this::convertToResourceProfile)
                .collect(Collectors.toList());
        int usersCount = users.size();
        return new PageImpl<>(users,pageable,usersCount);
    }

    private PaymentMethod convertToEntityPaymentMethod(SavePaymentMethodResource resource){
        return mapper.map(resource,PaymentMethod.class);
    }

    private PaymentMethodResource convertToResourcePaymentMethod(PaymentMethod entity){
        return mapper.map(entity,PaymentMethodResource.class);
    }
    private ProfileResource convertToResourceProfile(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }

}
