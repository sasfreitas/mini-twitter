package com.photonshoot.minitwitter.controller;

import com.photonshoot.minitwitter.config.AppConfig;
import com.photonshoot.minitwitter.domain.Tweets;
import com.photonshoot.minitwitter.model.Tweet;
import com.photonshoot.minitwitter.repository.TweetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class TweetControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private TweetRepository tweetRepositoryMock;

    @InjectMocks
    TweetController tweetController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tweetController).build();
    }


    @Test
    public void listCallTest() throws Exception {
        mockMvc.perform(get("/user/1/tweets")).andExpect(status().isOk());
    }

    @Test
    public void listTest() {
        long userId = 1;
        String text = "test";

        Tweet tweet = new Tweet();
        tweet.setUserId(userId);
        tweet.setText(text);
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet);

        when(tweetRepositoryMock.list(userId)).thenReturn(tweets);

        Tweets tweetsResponse = tweetController.list(userId);

        assertThat(tweetsResponse.getTweets().get(0).getUserId(), is(userId));
        assertThat(tweetsResponse.getTweets().get(0).getText(), is(text));

        verify(tweetRepositoryMock, times(1)).list(userId);
        verifyNoMoreInteractions(tweetRepositoryMock);
    }
}
