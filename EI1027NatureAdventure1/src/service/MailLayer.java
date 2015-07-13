package service;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import classes.Client;

public class MailLayer implements MessageSourceAware{

	private MessageSource messageSource;
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
    
    public void enviarMailRegistrado(Client client){
    	String to = client.getClientEmail();
    	String message = messageSource.getMessage("mail.message",null, LocaleContextHolder.getLocale());
    	this.sendMessage(message, to);
    }

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource= messageSource;
		
	}

}