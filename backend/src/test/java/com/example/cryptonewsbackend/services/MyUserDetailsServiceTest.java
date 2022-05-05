package com.example.cryptonewsbackend.services;

import com.example.cryptonewsbackend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MyUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserRepository userRepository;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadUserByUsername() {
        userRepository.findByUsername("root");
    }
}