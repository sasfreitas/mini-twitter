package com.photonshoot.minitwitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tweet {

    private long userId;

    private String text;

    @JsonIgnore
    private long created;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
