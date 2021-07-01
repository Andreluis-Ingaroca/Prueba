package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.PaymentMethod;
import com.roomies.roomies.domain.model.Plan;
import com.roomies.roomies.domain.model.Profile;
import com.roomies.roomies.domain.model.User;
import com.roomies.roomies.domain.repository.PaymentMethodRepository;
import com.roomies.roomies.domain.repository.PlanRepository;
import com.roomies.roomies.domain.repository.ProfileRepository;
import com.roomies.roomies.domain.repository.UserRepository;
import com.roomies.roomies.domain.service.ProfileService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Page<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
    }

    @Override
    public Profile createProfile(Long userId,Long planId,Profile profile) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan","Id",planId));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        Pageable pageable = PageRequest.of(0,10000);
        Page<Profile> profilePage= profileRepository.findAll(pageable);
        profilePage.forEach(profile1 -> {
            if(profile1.getUser().equals(user))
                throw new ResourceNotFoundException("The user is associated to another profile");
        });

        Date date =  new Date();
        if(date.getYear()-profile.getBirthday().getYear()>18)
            throw new ResourceNotFoundException("The user have to be older than 18");

        return profileRepository.save(profile.setUser(user)
        .setPlan(plan));
    }

    @Override
    public Profile updateProfile(Long profileId, Profile profileRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()-> new ResourceNotFoundException("Profile","Id",profileId));
        profile.setAddress(profileRequest.getAddress())
                .setBirthday(profileRequest.getBirthday())
                .setCellphone(profileRequest.getCellphone())
                .setDepartment(profileRequest.getDepartment())
                .setDescription(profileRequest.getDescription())
                //.setIdCard(profileRequest.getIdCard())
                .setName(profileRequest.getName())
                .setProvince(profileRequest.getProvince())
                .setLastName(profileRequest.getLastName())
                .setDistrict(profileRequest.getDistrict());
        return profileRepository.save(profile);
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()-> new ResourceNotFoundException("Profile","Id",profileId));
        profileRepository.delete(profile);
        return ResponseEntity.ok().build();
    }

    @Override
    public Profile assignPaymentMethodProfile(Long paymentMethodId, Long profileId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
        return profileRepository.findById(profileId).map(
                user -> profileRepository.save(user.assignWithPm(paymentMethod)))
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile","Id",profileId));
    }

    @Override
    public Profile unAssignPaymentMethodProfile(Long paymentMethodId, Long profileId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
        return profileRepository.findById(profileId).map(
                user -> profileRepository.save(user.unAssignWithPm(paymentMethod)))
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile","Id",profileId));
    }

    @Override
    public Page<Profile> getAllProfilesByPaymentMethodId(Long paymentMethodId, Pageable pageable) {
        return paymentMethodRepository.findById(paymentMethodId).map(
                paymentMethod ->{List<Profile> profiles = paymentMethod.getProfilesPaymentMethods();
                    int usersCount = profiles.size();
                    return new PageImpl<>(profiles,pageable,usersCount);
                }).orElseThrow(()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
    }

    @Override
    public Profile getProfileByName(String name) {
        return profileRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Profile","Name",name));
    }


}
