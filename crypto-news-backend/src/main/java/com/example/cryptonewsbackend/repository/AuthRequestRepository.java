package com.example.cryptonewsbackend.repository;

import com.example.cryptonewsbackend.model.AuthenticationRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Salah Abdinoor
 * 1/8/2022
 * 5:29 PM
 * crypto-news-backend
 * Copyright: MIT
 */
public interface AuthRequestRepository extends CrudRepository<AuthenticationRequest, Long> {

    Optional<AuthenticationRequest> findById(Long id);
    Optional<AuthenticationRequest> findByUsername(String username);
}