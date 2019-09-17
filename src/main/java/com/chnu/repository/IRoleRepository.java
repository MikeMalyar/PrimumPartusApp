package com.chnu.repository;

import com.chnu.model.Role;

import java.util.Optional;

public interface IRoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByRole(String role);

}
