package com.photonshoot.minitwitter.domain;

import com.photonshoot.minitwitter.model.Follower;

import java.util.List;

public class Followers {

    private List<Follower> followers;

    public Followers(List<Follower> followers) {
        this.followers = followers;
    }

    public List<Follower> getFollowers() {
        return followers;
    }
}
