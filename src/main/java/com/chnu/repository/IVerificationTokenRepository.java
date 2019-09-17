package com.chnu.repository;

import com.chnu.model.VerificationToken;

import java.util.Optional;

public interface IVerificationTokenRepository extends BaseRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);
}
