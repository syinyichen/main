package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;

/**
 * Represents a confirmation email, to confirm that the user email is a valid email.
 */
public class ConfirmationEmail {
    private final Name userName;
    private final Email userEmail;
    private MimeMessage message;

    private final int PIN;

    private final String emailUsername = "noreply.loansharkie";
    private final String emailPassword = "cs2103t-w12-3";

    /**
     * Constructs a ConfirmationEmail object.
     */
    public ConfirmationEmail(Name name, Email email) {
        requireAllNonNull(name, email);
        this.userName = name;
        this.userEmail = email;

        Random random = new Random();
        this.PIN = random.nextInt((9999 - 100) + 1) + 10;
    }

    /**
     * Sends an confirmation email to the user.
     * @throws MessagingException if error occurs while sending the email.
     */
    public void sendConfirmationEmail() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailUsername, emailPassword);
            }
        });

        message = new MimeMessage(session);
        constructMessage();

        Transport.send(message);
    }

    /**
     * Constructs the content of the email.
     * @return A message to be sent.
     * @throws MessagingException if error occurs while sending the email.
     */
    private MimeMessage constructMessage() throws MessagingException {
        String content = "<p>Dear " + userName + ",</p><br><p>Thank you for using&nbsp;<strong>"
                + "Sharkie</strong>!</p><br><p>Your confirmation PIN is&nbsp;<span style=\"text-decoration: "
                + "underline;\"><span style=\"color: #ff0000;\"><strong><span style=\"background-color: #ffff00;\">"
                + PIN + "</span></strong></span></span><span>.</p><br><p>Have a good day and enjoy your experience "
                + "at&nbsp;<strong>Sharkie</strong>! :)</p><br><p>Regards,</p><p>Sharkie.</p>";

        message.setFrom(new InternetAddress("noreply.loansharkie@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail.toString()));
        message.setSubject("[Sharkie] Confirmation of signing into Sharkie");
        message.setContent(content, "text/html; charset=utf-8");

        return message;
    }

    public Name getName() {
        return this.userName;
    }

    public Email getEmail() {
        return this.userEmail;
    }

    /**
     * Returns true if both userName and userEmail are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ConfirmationEmail)) {
            return false;
        }

        ConfirmationEmail otherConfirmationEmail = (ConfirmationEmail) other;
        return otherConfirmationEmail.getName().equals(getName())
                && otherConfirmationEmail.getEmail().equals(getEmail());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(userName, userEmail);
    }
}
