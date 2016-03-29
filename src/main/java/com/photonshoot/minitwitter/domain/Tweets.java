package com.photonshoot.minitwitter.domain;

import com.photonshoot.minitwitter.model.Tweet;

import java.util.List;

public class Tweets {

    private List<Tweet> tweets;

    public Tweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
