package com.roomies.roomies.resource;

import com.roomies.roomies.domain.model.AuditModel;

public class MessageResource extends AuditModel {
    private Long id;
    private String content;
    private ConversationResource landlordConversation;
    private ConversationResource leaseholderConversation;

    public Long getId() {
        return id;
    }

    public MessageResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageResource setContent(String content) {
        this.content = content;
        return this;
    }

    public ConversationResource getLandlordConversation() {
        return landlordConversation;
    }

    public MessageResource setLandlordConversation(ConversationResource landlordConversation) {
        this.landlordConversation = landlordConversation;
        return this;
    }

    public ConversationResource getLeaseholderConversation() {
        return leaseholderConversation;
    }

    public MessageResource setLeaseholderConversation(ConversationResource leaseholderConversation) {
        this.leaseholderConversation = leaseholderConversation;
        return this;
    }
}
