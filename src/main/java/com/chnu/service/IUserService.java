package com.chnu.service;

import com.chnu.dto.UserDTO;
import com.chnu.wrapper.UserLoginWrapper;
import com.chnu.wrapper.UserRegistrationWrapper;

public interface IUserService {

    UserDTO register(UserRegistrationWrapper wrapper);

    boolean checkEmailAvailable(String email);

    UserDTO login(UserLoginWrapper wrapper);

    boolean confirmRegistration(String token);
}
