package com.chnu.service;

import com.chnu.model.User;
import com.chnu.wrapper.UserLoginWrapper;
import com.chnu.wrapper.UserRegistrationWrapper;

public interface IUserService {

    User register(UserRegistrationWrapper wrapper);

    boolean checkEmailAvailable(String email);

    User login(UserLoginWrapper wrapper);

    boolean confirmRegistration(String token);
}
