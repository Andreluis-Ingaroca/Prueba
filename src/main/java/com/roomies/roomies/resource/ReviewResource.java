package com.roomies.roomies.resource;

import com.roomies.roomies.domain.model.AuditModel;

public class ReviewResource extends AuditModel {

    private Long id;
    private String content;
    private int starQuantity;
    private PostResource post;
    private ProfileResource profile;

    public Long getId() {
        return id;
    }

    public ReviewResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ReviewResource setContent(String content) {
        this.content = content;
        return this;
    }

    public int getStarQuantity() {
        return starQuantity;
    }

    public ReviewResource setStarQuantity(int starQuantity) {
        this.starQuantity = starQuantity;
        return this;
    }

    public PostResource getPost() {
        return post;
    }

    public ReviewResource setPost(PostResource post) {
        this.post = post;
        return this;
    }

    public ProfileResource getProfile() {
        return profile;
    }

    public ReviewResource setProfile(ProfileResource profile) {
        this.profile = profile;
        return this;
    }
}
