package com.photonshoot.minitwitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Follower {

    private long userId;

    private long followeeId;

    @JsonIgnore
    private long created;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(long followeeId) {
        this.followeeId = followeeId;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
