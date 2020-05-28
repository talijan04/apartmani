package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.User;

import java.util.Optional;

public interface IUserDao {
    Optional<User> findByUserName(String userName);

    int insertUser(User user);

}
