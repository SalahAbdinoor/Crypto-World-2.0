package com.example.cryptonewsbackend.repository;

import com.example.cryptonewsbackend.model.AuthenticationRequest;
import com.example.cryptonewsbackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    Iterable<User> findAll();

    List<User> findByUsername(String username);

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByMail(String mail);

}
