package com.example.cryptonewsbackend.services;

import com.example.cryptonewsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username).get(0);

        if (username.equalsIgnoreCase(user.getUsername())){
            System.out.println("User found");
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        } else {
            System.out.println("User not found, root assigned");
            return new User("root", "root" , new ArrayList<>());
        }

    }
}
