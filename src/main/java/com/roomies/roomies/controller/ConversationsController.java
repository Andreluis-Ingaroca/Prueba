package com.roomies.roomies.controller;

import com.roomies.roomies.domain.model.Conversation;
import com.roomies.roomies.domain.model.Message;
import com.roomies.roomies.domain.service.ConversationService;
import com.roomies.roomies.domain.service.MessageService;
import com.roomies.roomies.resource.ConversationResource;
import com.roomies.roomies.resource.MessageResource;
import com.roomies.roomies.resource.SaveMessageResource;
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
public class ConversationsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageService messageService;

    @DeleteMapping("/conversations/{conversationId}")
    public ResponseEntity<?> deleteConversation(@PathVariable Long conversationId){
        return conversationService.deleteConversation(conversationId);
    }

    @PostMapping("/conversations/{conversationSenderId}/messages")
    public MessageResource createMessage(
            @PathVariable (name = "conversationSenderId") Long conversationSenderId,
            @Valid @RequestBody SaveMessageResource resource){
        return convertToResourceMessage(messageService.createMessage(conversationSenderId,convertToEntityMessage(resource)));
    }

    private Message convertToEntityMessage(SaveMessageResource resource){
        return mapper.map(resource,Message.class);
    }

    private MessageResource convertToResourceMessage(Message entity){
        return  mapper.map(entity,MessageResource.class);
    }

}
