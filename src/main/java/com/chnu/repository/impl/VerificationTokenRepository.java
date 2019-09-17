package com.chnu.repository.impl;

import com.chnu.model.VerificationToken;
import com.chnu.repository.IVerificationTokenRepository;
import com.chnu.util.QueryUtility;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VerificationTokenRepository extends AbstractRepository<VerificationToken, Long>
        implements IVerificationTokenRepository {

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        if(token == null)
            return Optional.empty();
        return QueryUtility.findOrEmpty(() -> {
            List list = createNamedQuery(VerificationToken.FIND_BY_TOKEN)
                    .setParameter("token", token).list();
            if(!list.isEmpty())
                return (VerificationToken) list.get(0);
            return null;
        });
    }
}
