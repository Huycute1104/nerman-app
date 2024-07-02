package com.example.nermanapp.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
    void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException;
}
