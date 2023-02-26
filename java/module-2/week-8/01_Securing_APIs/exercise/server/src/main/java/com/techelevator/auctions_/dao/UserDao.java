package com.techelevator.auctions_.dao;

import com.techelevator.auctions_.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByUsername(String username);

}
