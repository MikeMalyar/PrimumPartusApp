package com.chnu.service.impl;

import com.chnu.dto.LetterDTO;
import com.chnu.service.ILetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterService implements ILetterService {

    private final MailService mailService;

    @Autowired
    public LetterService(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void sendEmailVerificationLetter(String email, String link) {
        LetterDTO letter = new LetterDTO();
        letter.setEmail(email);
        letter.setSubject("PrimumPartus - email registration confirmation");

        String content = "This email was specified for registration on website PrimumPartus.<br>" +
                "To complete registration, please, go to the following link <a href=" + link + ">" + link + "<a> <br>";

        letter.setContent(content);

        mailService.sendEmail(letter);
    }
}
