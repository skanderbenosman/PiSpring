package tn.esprit.spring.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class SimpleEmailExampleController {
 
    @Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(String email,String message2) {
 
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(email);
        message.setSubject("Test Simple Email");
        message.setText(message2);
 
        // Send Message!
        this.emailSender.send(message);
 
        return "Email Sent!";
    }
 
}