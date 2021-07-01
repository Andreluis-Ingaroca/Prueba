package com.roomies.roomies.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveReviewResource {
    @NotNull
    @Lob
    private String content;

    @NotNull
    private int starQuantity;

    public String getContent() {
        return content;
    }

    public SaveReviewResource setContent(String content) {
        this.content = content;
        return this;
    }

    public int getStarQuantity() {
        return starQuantity;
    }

    public SaveReviewResource setStarQuantity(int starQuantity) {
        this.starQuantity = starQuantity;
        return this;
    }
}
