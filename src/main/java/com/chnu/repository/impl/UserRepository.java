package com.chnu.repository.impl;

import com.chnu.model.User;
import com.chnu.repository.IUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractRepository<User, Long> implements IUserRepository {
}
