package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.Conversation;
import com.roomies.roomies.domain.model.Message;
import com.roomies.roomies.domain.repository.ConversationRepository;
import com.roomies.roomies.domain.repository.MessageRepository;
import com.roomies.roomies.domain.repository.ProfileRepository;
import com.roomies.roomies.domain.service.MessageService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Page<Message> getAllMessages(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(()->new ResourceNotFoundException("Message","Id",messageId));
    }

    @Override
    public Message createMessage(Long conversationSenderId,Message message) {
        Conversation sender = conversationRepository.findById(conversationSenderId)
                .orElseThrow(()->new ResourceNotFoundException("Conversation","Id",conversationSenderId));
        Long receiverId = sender.getReceiver().getId();
        Long senderId = sender.getSender().getId();

        Pageable pageable = PageRequest.of(0,1000);
        Page<Conversation> conversationPage = conversationRepository.findBySenderId(receiverId,pageable);

        if(conversationPage!=null) {
            List<Conversation> conversationList = conversationPage.toList();
            for (Conversation conversation : conversationList) {
                if (conversation.getReceiver().equals(sender.getSender())) {
                    return messageRepository.save(message.setConversationSender(sender).setConversationReceiver(conversation));
                }
            }
        }

        Conversation conversationReceiver = new Conversation()
                .setReceiver(sender.getSender())
                .setSender(sender.getReceiver());
        conversationRepository.save(conversationReceiver);
        return messageRepository.save(message.setConversationSender(sender).setConversationReceiver(conversationReceiver));
    }

    @Override
    public Message updateMessage(Long messageId, Message messageRequest) {
        Message message =messageRepository.findById(messageId)
                .orElseThrow(()->new ResourceNotFoundException("Message","Id",messageId));
        message.setContent(messageRequest.getContent());
        return messageRepository.save(message);
    }

    @Override
    public ResponseEntity<?> deleteMessage(Long messageId) {
        Message message =messageRepository.findById(messageId)
                .orElseThrow(()->new ResourceNotFoundException("Message","Id",messageId));
        messageRepository.delete(message);
        return ResponseEntity.ok().build();
    }
}
