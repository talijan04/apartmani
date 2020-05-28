package com.talijan04.testiranje.apartmani.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
       User user = new User(1,"admin","test2004",true,"ROLE_ADMIN");
       Assertions.assertEquals(1, user.getId());
    }

    @Test
    void setId() {
        User user = new User();
        user.setId(1);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    void getUserName() {
        User user = new User(1,"admin","test2004",true,"ROLE_ADMIN");
        Assertions.assertEquals("admin", user.getUserName());
    }

    @Test
    void setUserName() {
        User user = new User();
        user.setUserName("admin");
        Assertions.assertEquals("admin", user.getUserName());
    }

    @Test
    void getPassword() {
        User user = new User(1,"admin","test2004",true,"ROLE_ADMIN");
        Assertions.assertEquals("test2004", user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User();
        user.setPassword("test2004");
        Assertions.assertEquals("test2004", user.getPassword());
    }

    @Test
    void isActive() {
        User user = new User(1,"admin","test2004",true,"ROLE_ADMIN");
        Assertions.assertEquals(true, user.isActive());
    }

    @Test
    void setActive() {
        User user = new User();
        user.setActive(true);
        Assertions.assertEquals(true, user.isActive());
    }

    @Test
    void getRoles() {
        User user = new User(1,"admin","test2004",true,"ROLE_ADMIN");
        Assertions.assertEquals("ROLE_ADMIN", user.getRoles());
    }

    @Test
    void setRoles() {
        User user = new User();
        user.setRoles("ROLE_ADMIN");
        Assertions.assertEquals("ROLE_ADMIN", user.getRoles());
    }

    @Test
    void testToString() {
        User user = new User(1,"admin","test2004",true,"ROLE_ADMIN");
        String expected = "User{Id=1, userName='admin', password='test2004', active=true, roles='ROLE_ADMIN'}";
        Assertions.assertEquals(expected, user.toString());

    }
}