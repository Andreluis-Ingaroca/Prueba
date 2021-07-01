package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ConversationService {
    Page<Conversation> getAllConversationsByProfileId(Long profileId,Pageable pageable);
    Conversation getConversationById(Long conversationId);
    Conversation createConversation(Long profileSenderId,Long profileReceiverId);
    ResponseEntity<?> deleteConversation(Long conversationId);

}
