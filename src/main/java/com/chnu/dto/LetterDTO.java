package com.chnu.dto;

public class LetterDTO {

    private String email;
    private String subject;
    private String content;

    public String getEmail() {
        return email;
    }

    public LetterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public LetterDTO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LetterDTO setContent(String content) {
        this.content = content;
        return this;
    }
}
