package com.chnu.service;

import com.chnu.model.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> save(User user);
}
