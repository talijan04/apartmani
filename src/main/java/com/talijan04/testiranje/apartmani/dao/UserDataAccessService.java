package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("postgres2")
public class UserDataAccessService implements IUserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Ovde vraća korisnika koga tražimo u bazi da bi se logovali
    @Override
    public Optional<User> findByUserName(String userName) {
        final String SQL_SELECT_USER_BY_ID = "SELECT id, username, active, password FROM users WHERE username = ?";
        User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new Object[]{userName}, new UserRowMapper());
        System.out.println("2: "+user.toString());
        return Optional.ofNullable(user);

    }

    @Override
    public int insertUser(User user) {
        final String SQL_INSERT = "INSERT INTO users (id, username, password, active, roles) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL_INSERT,user.getId(), user.getUserName(), user.getPassword(), true ,user.getRoles());
        return 1;
    }


}
