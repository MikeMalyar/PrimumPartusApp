package com.chnu.service;

public interface ILetterService {

    void sendEmailVerificationLetter(String email, String token);
}
