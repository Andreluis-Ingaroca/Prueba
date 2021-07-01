package com.roomies.roomies.controller;

import com.roomies.roomies.domain.model.Conversation;
import com.roomies.roomies.domain.model.PaymentMethod;
import com.roomies.roomies.domain.model.Profile;
import com.roomies.roomies.domain.model.Review;
import com.roomies.roomies.domain.service.ConversationService;
import com.roomies.roomies.domain.service.PaymentMethodService;
import com.roomies.roomies.domain.service.ProfileService;
import com.roomies.roomies.domain.service.ReviewService;
import com.roomies.roomies.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class ProfilesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(Pageable pageable){
        List<ProfileResource> users = profileService.getAllProfiles(pageable)
                .getContent().stream().map(this::convertToResourceProfile)
                .collect(Collectors.toList());
        int usersCount = users.size();
        return new PageImpl<>(users,pageable,usersCount);
    }


    @PostMapping("/profiles/{profileId}/posts/{postId}/reviews")
    public ReviewResource createReview(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "postId") Long postId,
            @Valid @RequestBody SaveReviewResource resource){
        return convertToResourceReview(reviewService.createReview(postId,profileId,convertToEntityReview(resource)));
    }


    @PostMapping("profiles/{profileId}/methods/{methodId}")
    public ProfileResource assignProfilePaymentMethod(@PathVariable Long profileId,@PathVariable Long methodId){
        return convertToResourceProfile(profileService.assignPaymentMethodProfile(methodId,profileId));
    }


    @DeleteMapping("profiles/{profileId}/methods/{methodId}")
    public ProfileResource unassignProfilePaymentMethod(@PathVariable Long profileId,@PathVariable Long methodId){
        return convertToResourceProfile(profileService.unAssignPaymentMethodProfile(methodId,profileId));
    }


    @GetMapping("/profile/{profileId}/methods")
    public Page<PaymentMethodResource> getAllPaymentMethodsByProfileId(
            @PathVariable (name = "profileId") Long profileId,
            Pageable pageable){
        Page<PaymentMethod> paymentMethodPage = paymentMethodService.getAllPaymentMethodsByProfileId(profileId,pageable);
        List<PaymentMethodResource> resources = paymentMethodPage.getContent().stream().map(
                this::convertToResourcePaymentMethod).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }


    @GetMapping("/profiles/{profileId}/conversations")
    public Page<ConversationResource> getAllConversationsBySenderId(@PathVariable Long profileId, Pageable pageable){
        Page<Conversation> conversationPage = conversationService.getAllConversationsByProfileId(profileId,pageable);
        List<ConversationResource> resources = conversationPage.getContent().stream().map(
                this::convertToResourceConversation).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }


    @PostMapping("profiles/{profileSenderId}/profiles/{profileReceiverId}/conversations")
    public ConversationResource createConversation(
            @PathVariable (name = "profileSenderId") Long profileSenderId,
            @PathVariable (name ="profileReceiverId") Long profileReceiverId) {
        return convertToResourceConversation(conversationService.createConversation(profileSenderId,profileReceiverId));
    }

    private Profile convertToEntityProfile(SaveProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResourceProfile(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }

    private Review convertToEntityReview(SaveReviewResource resource) {
        return mapper.map(resource, Review.class);
    }

    private ReviewResource convertToResourceReview(Review entity) {
        return mapper.map(entity, ReviewResource.class);
    }

    private PaymentMethodResource convertToResourcePaymentMethod(PaymentMethod entity){
        return mapper.map(entity,PaymentMethodResource.class);
    }
    private ConversationResource convertToResourceConversation(Conversation entity){
        return mapper.map(entity, ConversationResource.class);
    }
}
