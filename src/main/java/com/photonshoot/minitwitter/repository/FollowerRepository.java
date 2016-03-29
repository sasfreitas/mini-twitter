package com.photonshoot.minitwitter.repository;

import com.photonshoot.minitwitter.exception.AlreadyFollowsException;
import com.photonshoot.minitwitter.exception.NotFollowingException;
import com.photonshoot.minitwitter.model.Follower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowerRepository {

    private static final String INSERT_SQL = "INSERT INTO `follower` (`USER_ID`, `FOLLOWEE_ID`) VALUES (?,?)";

    private static final String DELETE_SQL = "DELETE FROM `follower` WHERE `USER_ID`=? AND `FOLLOWEE_ID`=?";

    private static final String LIST_SQL = "SELECT `USER_ID`, `FOLLOWEE_ID` FROM `follower` WHERE `USER_ID`=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Follower> list(long userId) {
        Object[] args = new Object[]{userId};
        List<Follower> follower  = jdbcTemplate.query(LIST_SQL, args, new BeanPropertyRowMapper<Follower>(Follower.class));

        return follower;
    }

    public void create(long userId, long followerId) throws AlreadyFollowsException, NotFollowingException {
        Object[] args = new Object[]{userId, followerId};

        try {
            jdbcTemplate.update(INSERT_SQL, args);
        } catch (DuplicateKeyException e) {
            throw new AlreadyFollowsException();
        } catch (DataIntegrityViolationException e) {
            throw new NotFollowingException();
        }
    }

    public void delete(long userId, long followerId) throws NotFollowingException {
        Object[] args = new Object[]{userId, followerId};

        if(jdbcTemplate.update(DELETE_SQL, args) == 0) {
           throw new NotFollowingException();
        }
    }
}
