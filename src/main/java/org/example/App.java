package org.example;

import javax.mail.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NoSuchProviderException {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("Locationtoproperties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = Session.getDefaultInstance(props,null);
        Store store = session.getStore("imaps");
        try {
            store.connect("smtp.gmail.com","username","password");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Folder inbox = null;
        try {
            inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();
            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");

            for (int i = 0; i < 10; i++) {
                System.out.println("Mail Subject:- " + messages[i].getSubject());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
