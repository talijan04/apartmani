package com.talijan04.testiranje.apartmani.service;

import com.talijan04.testiranje.apartmani.dao.IUserDao;
import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.MyUserDetails;
import com.talijan04.testiranje.apartmani.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final IUserDao iUserDao;

@Autowired
    public MyUserDetailsService(@Qualifier("postgres2") IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = iUserDao.findByUserName(s);
        System.out.println("3: "+user.toString());
       // user.orElseThrow(()->new UsernameNotFoundException("Not found " + s));

      //  MyUserDetails myUserDetails = new MyUserDetails();
        //    myUserDetails.setPassword("test2004");
        //    myUserDetails.setUserName("admin");}
            return user.map(MyUserDetails::new).get();
      // return  myUserDetails;
    }

    public int addUser(User user){

        return iUserDao.insertUser(user);
    }

    /*
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user  = IUserDao.findByUserName(userName);
        user.orElseThrow(()->new UsernameNotFoundException("Not found " + userName));
        return user.map(MyUserDetails::new).get();
    }*/

    /*
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new MyUserDetails(s);
    }
    */

}
