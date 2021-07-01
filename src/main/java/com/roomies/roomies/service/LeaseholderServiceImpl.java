package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.*;
import com.roomies.roomies.domain.repository.*;
import com.roomies.roomies.domain.service.LeaseholderService;
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
public class LeaseholderServiceImpl implements LeaseholderService {

    @Autowired
    private LeaseholderRepository leaseholderRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Leaseholder> getAllLeaseholder(Pageable pageable) {
        return leaseholderRepository.findAll(pageable);
    }

    @Override
    public Leaseholder getLeaseholderById(Long leaseholderId) {
        return leaseholderRepository.findById(leaseholderId)
                .orElseThrow(()->new ResourceNotFoundException("Leaseholder","Id",leaseholderId));
    }

    @Override
    public Leaseholder createLeaseholder(Long userId,Long planId,Leaseholder leaseholder) {
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
        if(date.getYear()-leaseholder.getBirthday().getYear()>18)
            throw new ResourceNotFoundException("The user have to be older than 18");
        leaseholder.setPlan(plan).setUser(user);
        return leaseholderRepository.save(leaseholder);
    }

    @Override
    public Leaseholder updateLeaseholder(Long leaseholderId, Leaseholder leaseholderRequest) {
        Leaseholder leaseholder = leaseholderRepository.findById(leaseholderId)
                .orElseThrow(()->new ResourceNotFoundException("Leaseholder","Id",leaseholderId));
        leaseholder.setAddress(leaseholderRequest.getAddress())
                .setBirthday(leaseholderRequest.getBirthday())
                .setCellphone(leaseholderRequest.getCellphone())
                .setDepartment(leaseholderRequest.getDepartment())
                .setDescription(leaseholderRequest.getDescription())
                //.setIdCard(leaseholderRequest.getIdCard())
                .setName(leaseholderRequest.getName())
                .setProvince(leaseholderRequest.getProvince())
                .setLastName(leaseholderRequest.getLastName())
                .setDistrict(leaseholderRequest.getDistrict());
        return leaseholderRepository.save(leaseholder);
    }

    @Override
    public ResponseEntity<?> deleteLeaseholder(Long leaseholderId) {
        Leaseholder leaseholder = leaseholderRepository.findById(leaseholderId)
                .orElseThrow(()->new ResourceNotFoundException("Leaseholder","Id",leaseholderId));

        Pageable pageable = PageRequest.of(0,10000);
        Page<Post> postPage = getAllPostsByLeaseholderId(leaseholderId,pageable);
        List<PaymentMethod> paymentMethodList = leaseholder.getProfilePaymentMethods();
        Page<Conversation> conversationPage = conversationRepository.findBySenderId(leaseholderId,pageable);
        if(postPage!=null)
            postPage.forEach(post -> {
                post.unAssignWith(leaseholder);
            });
        if(paymentMethodList!=null)
            paymentMethodList.forEach(paymentMethod -> {
                paymentMethod.getProfilesPaymentMethods().remove(leaseholder);
            });
        if(conversationPage!=null)
            conversationPage.forEach(conversation -> conversationRepository.delete(conversation));

        userRepository.delete(leaseholder.getUser());
        leaseholderRepository.delete(leaseholder);
        return ResponseEntity.ok().build();
    }

    @Override
    public Leaseholder assignLeaseholderPost(Long leaseholderId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        return leaseholderRepository.findById(postId).map(
                leaseholder -> leaseholderRepository.save(leaseholder.assignWith(post)))
                .orElseThrow(()->new ResourceNotFoundException("Leaseholder","Id",leaseholderId));
    }

    @Override
    public Leaseholder unAssignLeaseholderPost(Long leaseholderId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        return leaseholderRepository.findById(postId).map(
                leaseholder -> leaseholderRepository.save(leaseholder.unAssignWith(post)))
                .orElseThrow(()->new ResourceNotFoundException("Leaseholder","Id",leaseholderId));
    }

    @Override
    public Page<Post> getAllPostsByLeaseholderId(Long leaseholderId, Pageable pageable) {
        return leaseholderRepository.findById(leaseholderId).map(
                leaseholder -> {
                    List<Post> posts = leaseholder.getFavouritePosts();
                    int postsCount = posts.size();
                    return new PageImpl<>(posts,pageable,postsCount);
                }).orElseThrow(()->new ResourceNotFoundException(
                        "Leaseholder","Id",leaseholderId));
    }

    @Override
    public Leaseholder getLeaseholderByName(String name) {
        return leaseholderRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Leaseholder","Name",name));
    }
}
