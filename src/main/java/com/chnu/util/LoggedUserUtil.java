package com.chnu.util;

import com.chnu.exception.UserNotFoundException;
import com.sun.security.auth.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class LoggedUserUtil {

    public static Optional<UserPrincipal> getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal) {
            return Optional.of((UserPrincipal) principal);
        }

        throw new UserNotFoundException("No such user");
    }

}
