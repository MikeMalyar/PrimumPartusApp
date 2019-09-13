package com.chnu.service.impl;

import com.chnu.model.User;
import com.chnu.repository.IUserRepository;
import com.chnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> save(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }
}
