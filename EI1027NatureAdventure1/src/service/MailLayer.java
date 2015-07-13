package service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailLayer{

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    
    public void sendMessage(String message, String to){
    	SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
    	msg.setTo(to);
    	msg.setText(message);
    	try{
    		this.mailSender.send(msg);
    	}
    	catch (MailException ex) {
    		System.err.println(ex.getMessage());
    	}
    }

}