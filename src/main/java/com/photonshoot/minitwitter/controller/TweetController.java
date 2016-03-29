package com.photonshoot.minitwitter.controller;

import com.photonshoot.minitwitter.domain.Tweets;
import com.photonshoot.minitwitter.model.Tweet;
import com.photonshoot.minitwitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @RequestMapping(value = "/user/{userId}/tweets", method = RequestMethod.GET)
    public @ResponseBody
    Tweets list(@PathVariable long userId) {
        List<Tweet> tweets = tweetRepository.list(userId);
        return new Tweets(tweets);
    }

    @RequestMapping(value = "/user/{userId}/tweets", params = "search", method = RequestMethod.GET)
    public @ResponseBody
    Tweets search(@PathVariable long userId, @RequestParam("search") String search) {
        List<Tweet> tweets = tweetRepository.list(userId, search);
        return new Tweets(tweets);
    }
}
