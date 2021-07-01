package com.roomies.roomies.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUserResource {
    @NotNull
    @Size(max = 100)
    @NaturalId
    private String email;

    @NotNull
    @Size(max = 100)
    @NaturalId
    private String password;

    public String getEmail() {
        return email;
    }

    public SaveUserResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
