package tn.esprit.spring.control;

import org.o7planning.sbmail.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class SimpleEmailExampleController {

    @Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(String s) {
 
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         message.setFrom(MyConstants.MY_EMAIL);
        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Mail de validation");
        message.setText("bonjour "+ s +" votre Achat à été effectuer avec succées");
 
        // Send Message!
        this.emailSender.send(message);
 
        return "Email Sent!";
    }
}
