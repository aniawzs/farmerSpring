package pl.annawyszomirskaszmyd.farmerspring.models.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.ConfirmationTokenEntity;

@Service
public class EmailSenderService{

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String emailAddress, ConfirmationTokenEntity confirmationToken){
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(emailAddress);
        email.setSubject("Rejestracja została zakończona!");
        email.setFrom("testingemailsspring@gmail.com");
        email.setText("Aby potwierdzić konto kliknij tutaj : "
                +"http://localhost:8080/confirm-account?token="+ confirmationToken.getConfirmationToken());

        javaMailSender.send(email);
    }
}
