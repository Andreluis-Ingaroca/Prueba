package com.roomies.roomies.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name ="conversations")
public class Conversation extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="profile_sender_id",nullable = false)
    @JsonIgnore
    private Profile sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="profile_receiver_id",nullable = false)
    @JsonIgnore
    private Profile receiver;

    public Conversation() {
    }

    public Long getId() {
        return id;
    }

    public Conversation setId(Long id) {
        this.id = id;
        return this;
    }

    public Profile getSender() {
        return sender;
    }

    public Conversation setSender(Profile sender) {
        this.sender = sender;
        return this;
    }
    public Profile getReceiver() {
        return receiver;
    }

    public Conversation setReceiver(Profile receiver) {
        this.receiver = receiver;
        return this;
    }
}
