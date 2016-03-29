package com.photonshoot.minitwitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    private String username;

    private String email;

    @JsonIgnore
    private long created;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
