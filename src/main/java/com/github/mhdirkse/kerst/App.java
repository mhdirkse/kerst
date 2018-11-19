package com.github.mhdirkse.kerst;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class App {

    public static void main(String[] args) throws IOException {
        final Properties credentials = loadProperties("/credentials");
        final String username = credentials.getProperty("username");
        final String password = credentials.getProperty("password");

        Session session = getSession(username, password);
        new MailSender(username, password, session).sendMail(
                "mhdirkse@live.nl", "Probeersel Java mail", "body.html");
    }

    private static Session getSession(final String username, final String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    private static Properties loadProperties(String resource) throws IOException {
        Properties prop = new Properties();
        InputStream in = App.class.getResourceAsStream(resource);
        prop.load(in);
        in.close();
        return prop;
    }
}
