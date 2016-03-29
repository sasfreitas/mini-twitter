package com.photonshoot.minitwitter.repository;

import com.photonshoot.minitwitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private static final String LIST_SQL = "SELECT `USERNAME`, `EMAIL` FROM `user`";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> list() {
        List<User> users  = jdbcTemplate.query(LIST_SQL, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
