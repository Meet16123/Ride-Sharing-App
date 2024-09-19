package com.meet.uber.uberapplication.service;

public interface EmailSenderService {
    void sendEmail(String toEmail, String subject, String body);

    void sendEmail(String[] toEmail, String subject, String body);


}
