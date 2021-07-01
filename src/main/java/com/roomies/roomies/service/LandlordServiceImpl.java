package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.*;
import com.roomies.roomies.domain.repository.*;
import com.roomies.roomies.domain.service.LandlordService;
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
public class LandlordServiceImpl implements LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Landlord> getAllLandlords(Pageable pageable) {
        return landlordRepository.findAll(pageable);
    }

    @Override
    public Page<Landlord> getAllLandlordsByDistrictAndProvinceAnd(Long landlordId, Pageable pageable) {
        Landlord landlord = landlordRepository.findById(landlordId)
                .orElseThrow(()->new ResourceNotFoundException("Landlord","Id",landlordId));
        String district = landlord.getDistrict();
        String province = landlord.getProvince();
        String department = landlord.getDepartment();
        return landlordRepository.findByProvinceAndDepartmentAndDistrict(province,department,district,pageable);
    }

    @Override
    public Landlord getLandlordById(Long landlordId) {
        return landlordRepository.findById(landlordId)
                .orElseThrow(()->new ResourceNotFoundException("Landlord","Id",landlordId));
    }

    @Override
    public Landlord createLandlord(Long userId,Long planId,Landlord landlord) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan","Id",planId));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        Pageable pageable = PageRequest.of(0,10000);
        Page<Profile> profilePage= profileRepository.findAll(pageable);
        profilePage.forEach(profile -> {
            if(profile.getUser().equals(user))
                throw new ResourceNotFoundException("The user is associated to another profile");
        });

        Date date =  new Date();
        if(date.getYear()-landlord.getBirthday().getYear()>18)
            throw new ResourceNotFoundException("The user have to be older than 18");
        landlord.setPlan(plan).setUser(user);
        return landlordRepository.save(landlord);
    }

    @Override
    public Landlord updateLandlord(Long landlordId, Landlord landlordRequest) {
        Landlord landlord = landlordRepository.findById(landlordId)
                .orElseThrow(()->new ResourceNotFoundException("Landlord","Id",landlordId));
        landlord.setAddress(landlordRequest.getAddress())
                .setBirthday(landlordRequest.getBirthday())
                .setCellphone(landlordRequest.getCellphone())
                .setDepartment(landlordRequest.getDepartment())
                .setDescription(landlordRequest.getDescription())
                //.setIdCard(landlordRequest.getIdCard())
                .setName(landlordRequest.getName())
                .setProvince(landlordRequest.getProvince())
                .setLastName(landlordRequest.getLastName())
                .setDistrict(landlordRequest.getDistrict());
        return landlordRepository.save(landlord);
    }

    @Override
    public ResponseEntity<?> deleteLandlord(Long landlordId) {
        Landlord landlord= landlordRepository.findById(landlordId)
                .orElseThrow(()->new ResourceNotFoundException("Landlord","Id",landlordId));

        Pageable pageable = PageRequest.of(0,10000);
        List<PaymentMethod> paymentMethodList = landlord.getProfilePaymentMethods();
        Page<Conversation> conversationPage = conversationRepository.findBySenderId(landlordId,pageable);
        Page<Post> postPage = getPostsByLandlordId(landlordId,pageable);

        if(paymentMethodList!=null)
            paymentMethodList.forEach(paymentMethod -> {
                paymentMethod.getProfilesPaymentMethods().remove(landlord);
            });
        if(conversationPage!=null)
            conversationPage.forEach(conversation -> conversationRepository.delete(conversation));
        if(postPage!=null)
            postPage.forEach(post -> postRepository.delete(post));

        userRepository.delete(landlord.getUser());
        landlordRepository.delete(landlord);
        return ResponseEntity.ok().build();
    }

    @Override
    public Landlord getLandlordByName(String name) {
        return landlordRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Landlord","Name",name));
    }

    @Override
    public Page<Post> getPostsByLandlordId(Long landlordId, Pageable pageable) {
        return landlordRepository.findById(landlordId).map(
                landlord -> {
                    List<Post> posts = landlord.getPosts();
                    int postCount = posts.size();
                    return new PageImpl<>(posts,pageable,postCount);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Landlord","Id",landlordId));
    }
}
