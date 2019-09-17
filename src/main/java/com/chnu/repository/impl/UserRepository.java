package com.chnu.repository.impl;

import com.chnu.model.User;
import com.chnu.repository.IUserRepository;
import com.chnu.util.QueryUtility;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends AbstractRepository<User, Long> implements IUserRepository {

    @Override
    public Optional<User> findByEmail(String email) {
        if(email == null)
            return Optional.empty();
        return QueryUtility.findOrEmpty(() -> {
            List list = createNamedQuery(User.FIND_BY_EMAIL)
                    .setParameter("email", email).list();
            if(!list.isEmpty())
                return (User) list.get(0);
            return null;
        });
    }
}
