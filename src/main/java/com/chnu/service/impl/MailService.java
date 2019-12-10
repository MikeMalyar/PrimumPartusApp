package com.chnu.service.impl;

import com.chnu.dto.LetterDTO;
import com.chnu.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

@Service
public class MailService implements IMailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Object object) {
        LetterDTO letter = (LetterDTO) object;
        MimeMessagePreparator preparator = getContent(letter);

        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            throw new EventException((short)0, e.getMessage());
        }
    }

    private MimeMessagePreparator getContent(final LetterDTO letter) {
        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setSubject(letter.getSubject());
            helper.setFrom("primumpartushelp@gmail.com");
            helper.setTo(letter.getEmail());
            String content = letter.getContent();
            helper.setText("<html><body><p>" + content + "</p></body></html>", true);
        };
    }
}
