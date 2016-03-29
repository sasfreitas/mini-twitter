package com.photonshoot.minitwitter.controller;

import com.photonshoot.minitwitter.domain.Followers;
import com.photonshoot.minitwitter.exception.AlreadyFollowsException;
import com.photonshoot.minitwitter.exception.NotFollowingException;
import com.photonshoot.minitwitter.model.Follower;
import com.photonshoot.minitwitter.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class FollowerController {

    @Autowired
    private FollowerRepository followerRepository;

    @RequestMapping(value = "/user/{userId}/followed", method = RequestMethod.GET)
    public @ResponseBody
    Followers list(@PathVariable long userId) {
        List<Follower> followers = followerRepository.list(userId);
        return new Followers(followers);
    }

    @RequestMapping(value = "/user/{userId}/follow/{followerId}", method = RequestMethod.PUT)
    public @ResponseBody void follow(@PathVariable long userId, @PathVariable long followerId, HttpServletResponse response) {
        try {
            followerRepository.create(userId, followerId);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (AlreadyFollowsException e) {
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NotFollowingException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/user/{userId}/follow/{followerId}", method = RequestMethod.DELETE)
    public @ResponseBody void unfollow(@PathVariable long userId, @PathVariable long followerId, HttpServletResponse response) {
        try {
            followerRepository.delete(userId, followerId);
        } catch (NotFollowingException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
