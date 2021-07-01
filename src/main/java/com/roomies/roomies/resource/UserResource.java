package com.roomies.roomies.resource;

public class UserResource {
    private Long id;
    private String email;
    private String password;
    private String token;

    public String getToken() {
        return token;
    }

    public UserResource setToken(String token) {
        this.token = token;
        return this;
    }


    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
