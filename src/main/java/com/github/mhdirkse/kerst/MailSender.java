package com.github.mhdirkse.kerst;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class MailSender {
    private final String username;
    private final String password;
    
    public MailSender(
            final String username,
            final String password) {
        this.username = username;
        this.password = password;
    }

    public void sendMail(String recipient, String subject, String bodyResource, String pictureResource)
            throws EmailException, IOException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setSSLOnConnect(true);
        email.setFrom(username);
        email.setSubject(subject);
        String htmlMsg = getText(bodyResource);
        String cid = email.embed(getFile(pictureResource));
        htmlMsg = htmlMsg.replaceAll("##cid##", cid);
        email.setHtmlMsg(htmlMsg);
        email.addTo(recipient);
        email.send();
        System.out.println("Sent mail to: " + recipient);
    }

    private String getText(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        return Resources.toString(url, Charsets.UTF_8);
    }

    private File getFile(String pictureResource) {
        URL url = this.getClass().getClassLoader().getResource(pictureResource);
        try {
            return new File(url.toURI());
        } catch(Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
