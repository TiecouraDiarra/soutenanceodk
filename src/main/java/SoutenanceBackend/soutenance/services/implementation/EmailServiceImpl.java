package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public class EmailServiceImpl implements EmailService {
    /*@Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }*/
}
