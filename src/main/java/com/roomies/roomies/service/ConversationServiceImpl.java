package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.Conversation;
import com.roomies.roomies.domain.model.Profile;
import com.roomies.roomies.domain.repository.ConversationRepository;
import com.roomies.roomies.domain.repository.ProfileRepository;
import com.roomies.roomies.domain.service.ConversationService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Conversation> getAllConversationsByProfileId(Long profileSenderId,Pageable pageable) {
        return conversationRepository.findBySenderId(profileSenderId,pageable);
    }

    @Override
    public Conversation getConversationById(Long conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(()->new ResourceNotFoundException("Conversation","Id",conversationId));
    }

    @Override
    public Conversation createConversation(Long profileSenderId,Long profileReceiverId) {
        Profile sender = profileRepository.findById(profileSenderId)
            .orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileSenderId));
        Profile receiver = profileRepository.findById(profileReceiverId)
                .orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileReceiverId));

        Pageable pageable = PageRequest.of(0,10000);
        Page<Conversation> conversationPage = conversationRepository.findBySenderId(profileReceiverId,pageable);
        if(conversationPage!=null) {
            List<Conversation> conversationList = conversationPage.toList();
            for (Conversation conversation : conversationList) {
                if (conversation.getReceiver().equals(sender))
                    return conversationRepository.save(new Conversation().setSender(sender).setReceiver(receiver));
            }
        }
        conversationRepository.save(new Conversation().setSender(receiver).setReceiver(sender));
        return conversationRepository.save(new Conversation().setSender(sender).setReceiver(receiver));
    }

    @Override
    public ResponseEntity<?> deleteConversation(Long conversationId) {
        Conversation conversation= conversationRepository.findById(conversationId)
                .orElseThrow(()->new ResourceNotFoundException("Conversation","Id",conversationId));
        conversationRepository.delete(conversation);
        return ResponseEntity.ok().build();
    }
}
