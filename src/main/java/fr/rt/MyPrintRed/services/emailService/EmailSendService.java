package fr.rt.MyPrintRed.services.emailService;


import fr.rt.MyPrintRed.repositories.EmailSenderRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


/**
 * @author slimane
 * @Project emailSpring
 */

@Service
public class EmailSendService implements EmailSenderRepository {

    public final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSendService.class);
    private final JavaMailSender javaMailSender;

    public EmailSendService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String email, String subject){

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("testmailcoolspring@gmail.com");
            javaMailSender.send(mimeMessage);
        } catch (
                MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

}
