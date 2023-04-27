package com.ali.springboot.springjdbctemplate.dao;

import com.ali.springboot.springjdbctemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepoImpl implements UserRepo {
    private static final String INSERT_USER_QUERY = "INSERT INTO user(id,fname,lname,email) VALUES(?,?,?,?)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE user SET fname=? WHERE ID=?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE ID=?";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM user WHERE ID=?";
    private static final String GET_USERS_QUERY = "SELECT * FROM user";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getFname(), user.getLname(), user.getEmail());
        return user;
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY, user.getFname(), user.getId());
        return user;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, (rs, rowNum) -> {
            return new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname")
                    , rs.getString("email"));
        },id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        List<Map<String, Object>> rows;
        rows = jdbcTemplate.queryForList(GET_USERS_QUERY);

        for (Map row : rows) {
            User obj = new User();

            obj.setId((int) row.get("id"));
            obj.setFname((String) row.get("fname"));
            obj.setLname((String) row.get("lname"));
            obj.setEmail((String) row.get("email"));
            users.add(obj);
        }

        return users;
    }

    @Override
    public String deleteById(int id) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
        return "DELETED!" + id;
    }
}
