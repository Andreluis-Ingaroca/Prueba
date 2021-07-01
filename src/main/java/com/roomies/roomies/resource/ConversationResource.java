package com.roomies.roomies.resource;

import com.roomies.roomies.domain.model.AuditModel;

public class ConversationResource extends AuditModel {
    private Long id;
    private ProfileResource sender;
    private ProfileResource receiver;

    public Long getId() {
        return id;
    }

    public ConversationResource setId(Long id) {
        this.id = id;
        return this;
    }

    public ProfileResource getSender() {
        return sender;
    }

    public ConversationResource setSender(ProfileResource sender) {
        this.sender = sender;
        return this;
    }

    public ProfileResource getReceiver() {
        return receiver;
    }

    public ConversationResource setReceiver(ProfileResource receiver) {
        this.receiver = receiver;
        return this;
    }
}