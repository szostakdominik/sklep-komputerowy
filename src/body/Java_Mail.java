package body;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Klasa, która umożliwia wysyłanie maili do poszczególnych użytkowników
 * @author Witold Wątroba
 */

public class Java_Mail {
    /**
     * metoda zawiera login hasło logowania oraz dane dotyczące serwera poczty e_mail
     * @param id
     * @param email 
     */
    public void java_Mail_send(String id, String email) {
        final String username = "testowyJavaMail@gmail.com";
        final String password = "projektisijava";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testowyJavaMail@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Testing Subject");
            message.setText("Drogi użytkowniku,"
                    + "\nDziękujemy za zakupiony przedmiot: " + id);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
