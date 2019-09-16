package com.chnu.service;

import com.chnu.model.User;

public interface IUserService {

    User register(User user);

    boolean checkEmailAvailable(String email);

    User login(User user);
}
