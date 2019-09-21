package com.chnu.service;

import com.chnu.dto.UserDTO;
import com.chnu.model.User;
import com.chnu.wrapper.UserLoginWrapper;
import com.chnu.wrapper.UserRegistrationWrapper;

import java.util.Optional;

public interface IUserService {

    UserDTO register(UserRegistrationWrapper wrapper);

    boolean checkEmailAvailable(String email);

    UserDTO login(UserLoginWrapper wrapper);

    boolean confirmRegistration(String token);

    Optional<User> findById(Long id);
}
