package com.roomies.roomies.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveMessageResource {

    @NotNull
    @Lob
    private String content;

    public String getContent() {
        return content;
    }

    public SaveMessageResource setContent(String content) {
        this.content = content;
        return this;
    }
}
