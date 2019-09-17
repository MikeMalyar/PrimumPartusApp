package com.chnu.repository.impl;

import com.chnu.model.Role;
import com.chnu.repository.IRoleRepository;
import com.chnu.util.QueryUtility;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository extends AbstractRepository<Role, Long> implements IRoleRepository {

    @Override
    public Optional<Role> findByRole(String role) {
        if(role == null)
            return Optional.empty();
        return QueryUtility.findOrEmpty(() -> {
            List list = createNamedQuery(Role.FIND_BY_ROLE)
                    .setParameter("role", role).list();
            if(!list.isEmpty())
                return (Role) list.get(0);
            return null;
        });
    }
}
