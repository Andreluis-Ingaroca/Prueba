package com.roomies.roomies.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="messages")
public class Message extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "conversation_sender_id",nullable = false)
    @JsonIgnore
    private Conversation conversationSender;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "conversation_receiver_id",nullable = false)
    @JsonIgnore
    private Conversation conversationReceiver;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Conversation getConversationSender() {
        return conversationSender;
    }

    public Message setConversationSender(Conversation conversationSender) {
        this.conversationSender = conversationSender;
        return this;
    }

    public Conversation getConversationReceiver() {
        return conversationReceiver;
    }

    public Message setConversationReceiver(Conversation conversationReceiver) {
        this.conversationReceiver = conversationReceiver;
        return this;
    }
}
