package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Landlord;
import com.roomies.roomies.domain.model.Leaseholder;
import com.roomies.roomies.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LandlordService {
    Page<Landlord> getAllLandlords(Pageable pageable);
    Page<Landlord> getAllLandlordsByDistrictAndProvinceAnd(Long landlordId,Pageable pageable);
    Landlord getLandlordById(Long landlordId);
    Landlord createLandlord(Long userId,Long planId,Landlord landlord);
    Landlord updateLandlord(Long landlordId,Landlord landlordRequest);
    ResponseEntity<?> deleteLandlord(Long landlordId);
    Landlord getLandlordByName(String name);
    Page<Post> getPostsByLandlordId(Long landlordId, Pageable pageable);

}
