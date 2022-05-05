package com.example.cryptonewsbackend.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String mail;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User() {
    }

    public User(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

}
