package com.github.mhdirkse.kerst;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
/**
 * Picture comes from: 46798617-verbazingwekkende-fee-huis-ingericht-met-kerstmis-in-de-vorm-van-elfs-hoed-met-geopende-deur-en-de-o
 * @author martijn
 *
 */
public class App {

    public static void main(String[] args) throws EmailException, IOException {
        final Properties credentials = loadProperties("/credentials");
        final String username = credentials.getProperty("username");
        final String password = credentials.getProperty("password");

        new InviterWithLots(new MailSender(username, password)).sendAll();
    }

    private static Properties loadProperties(String resource) throws IOException {
        Properties prop = new Properties();
        InputStream in = App.class.getResourceAsStream(resource);
        prop.load(in);
        in.close();
        return prop;
    }
}
