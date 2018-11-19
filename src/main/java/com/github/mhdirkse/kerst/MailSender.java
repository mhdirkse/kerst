package com.github.mhdirkse.kerst;

import java.io.IOException;
import java.net.URL;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class MailSender {
    private final String username;
    private final String password;
    private final Session session;
    
    public MailSender(
            final String username,
            final String password,
            final Session session) {
        this.username = username;
        this.password = password;
        this.session = session;
    }

    public void sendMail(String recipient, String subject, String bodyResource) throws IOException {
        try {
            Message message = new MimeMessage(session);
            message.addHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(getText(bodyResource), "text/html; charset=utf-8");
            Transport transport = session.getTransport("smtps");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Sent message to: " + recipient);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getText(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        return Resources.toString(url, Charsets.UTF_8);
    }
}
