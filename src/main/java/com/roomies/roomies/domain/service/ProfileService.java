package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Page<Profile> getAllProfiles(Pageable pageable);
    Profile getProfileById(Long profileId);
    Profile createProfile(Long userId,Long planId,Profile profile);
    Profile updateProfile(Long profileId, Profile profileRequest);
    ResponseEntity<?> deleteProfile(Long profileId);
    Profile assignPaymentMethodProfile(Long paymentMethodId, Long profileId);
    Profile unAssignPaymentMethodProfile(Long paymentMethodId, Long profileId);
    Page<Profile> getAllProfilesByPaymentMethodId(Long ProfileId, Pageable pageable);
    Profile getProfileByName(String name);
}
