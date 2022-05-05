package com.example.cryptonewsbackend.controller;

import com.example.cryptonewsbackend.configuration.SecurityConfig;
import com.example.cryptonewsbackend.model.User;
import com.example.cryptonewsbackend.repository.UserRepository;
import com.example.cryptonewsbackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    List addUser(
            @RequestBody HashMap data
          ){
        List list = new ArrayList();
        System.out.println("register Start");

        var password = data.get("password").toString();
        var mail = data.get("mail").toString();
        var username = data.get("name").toString();;

        System.out.println("password = " + password);
        System.out.println("mail = " + mail);
        System.out.println("username = " + username);
        String encodedPassword = securityConfig.passwordEncoder().encode(password);

        if (userRepository.findByMail(mail).isPresent()) {
            return Collections.singletonList(list.add("Account already exists"));

        } else {

            list.add(mail + ": Is Signed up! \n   Welcome " + username);
            userRepository.save(new User(username, encodedPassword, mail));
            return list;

        }
    }

    @GetMapping(path = "/get")
    public @ResponseBody
    List<String> getUsers(@RequestHeader("Authorization") String token){

        List<String> list = new ArrayList();

        var jwt = token.substring(7);

        var username = jwtUtil.extractUsername(jwt);
        var expiration = String.valueOf(jwtUtil.extractExpiration(jwt));

        list.add(username);
        list.add(expiration);

        return list;
    }

}
