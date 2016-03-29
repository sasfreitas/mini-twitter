package com.photonshoot.minitwitter.repository;

import com.photonshoot.minitwitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TweetRepository {
    private static final String LIST_SQL = "SELECT `USER_ID`, `TEXT`, `CREATED` FROM `tweet` WHERE `USER_ID`=? "
            + "OR `USER_ID` IN (SELECT `FOLLOWEE_ID` FROM `follower` WHERE `USER_ID`=?) ORDER BY `ID` DESC";

    private static final String SEARCH_SQL = "SELECT `USER_ID`, `TEXT`, `CREATED` FROM `tweet` WHERE (`USER_ID`=? "
            + "OR `USER_ID` IN (SELECT `FOLLOWEE_ID` FROM `follower` WHERE `USER_ID`=?)) AND MATCH(`TEXT`) AGAINST(?) ORDER BY `ID` DESC";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Tweet> list(long userId) {
        Object[] args = new Object[]{userId, userId};

        List<Tweet> tweets  = jdbcTemplate.query(LIST_SQL, args, new BeanPropertyRowMapper<Tweet>(Tweet.class));
        return tweets;
    }

    public List<Tweet> list(long userId, String search) {
        Object[] args = new Object[]{userId, userId, search};

        List<Tweet> tweets  = jdbcTemplate.query(SEARCH_SQL, args, new BeanPropertyRowMapper<Tweet>(Tweet.class));
        return tweets;
    }
}
