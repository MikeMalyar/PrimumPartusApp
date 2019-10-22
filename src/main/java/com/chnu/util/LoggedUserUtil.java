package com.chnu.util;

import com.chnu.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class LoggedUserUtil {

    public static Optional<User> getLoggedUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
            return Optional.of((User) user);
        }

        return Optional.empty();
    }

}
