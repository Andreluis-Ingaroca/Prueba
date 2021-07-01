package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    Page<Message> getAllMessages(Pageable pageable);
    Message getMessageById(Long messageId);
    Message createMessage(Long conversationSenderId,Message message);
    Message updateMessage(Long messageId,Message messageRequest);
    ResponseEntity<?> deleteMessage(Long messageId);
}
