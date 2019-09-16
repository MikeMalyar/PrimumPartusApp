package com.chnu.repository;

import com.chnu.model.User;

import java.util.Optional;

public interface IUserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
