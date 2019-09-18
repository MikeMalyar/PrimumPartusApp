package com.chnu.repository.impl;

import com.chnu.model.Profile;
import com.chnu.repository.IProfileRepository;
import com.chnu.util.QueryUtility;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProfileRepository extends AbstractRepository<Profile,Long> implements IProfileRepository {
    @Override
    public Optional<Profile> findByProfile(Long userId){
        if(userId==null){
            return Optional.empty();
        }
        return QueryUtility.findOrEmpty(() -> {
            List list = createNamedQuery(Profile.FIND_BY_PROFILE)
                    .setParameter("user_id", userId).list();
            if(!list.isEmpty())
                return (Profile) list.get(0);
            return null;
        });
    }
}
