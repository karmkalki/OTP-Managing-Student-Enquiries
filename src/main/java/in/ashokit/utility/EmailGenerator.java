package in.ashokit.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.ContentType;
import jakarta.mail.internet.MimeMessage;
@Component
public class EmailGenerator {

    @Autowired
    private JavaMailSender emailSender;
boolean issent=false;
    public boolean sendEmail(String to, String subject,String body){
try {
	
	
	MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message); 
    helper.setTo(to);
    helper.setSubject(subject);
    // Build HTML content with hyperlink
    helper.setText(body,true);//true represent body content html text
    emailSender.send(message);

issent=true;  	  
}
catch(Exception e) {
	e.printStackTrace();
	
}
return issent;  	
    }
 
}
