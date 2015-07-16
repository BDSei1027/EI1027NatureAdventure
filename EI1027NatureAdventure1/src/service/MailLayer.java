package service;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import classes.Client;
import classes.Email;

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
    
    public void sendMessage(String message, String to, String subject){
    	SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
    	msg.setTo(to);
    	msg.setText(message);
    	msg.setSubject(subject);
    	try{
    		this.mailSender.send(msg);
    	}
    	catch (MailException ex) {
    		System.err.println(ex.getMessage());
    	}
    }
    
    public void enviarMailRegistrado(Client client){
    	String to = client.getClientEmail();
    	String message = messageSource.getMessage("mail.messagenew",null, LocaleContextHolder.getLocale());
    	String subject = messageSource.getMessage("mail.messagenewsubject",null, LocaleContextHolder.getLocale());
    	this.sendMessage(message, to,subject);
    }

    
    public void enviarmailDeAdminHacia(String to, String message){
    	String subject = messageSource.getMessage("mail.messageadminsubject" ,null, LocaleContextHolder.getLocale());
    	this.sendMessage(message, to, subject);
    }
    
    
    public void sendPasswordRecovery(Email email, String token){
    	String message = messageSource.getMessage("mail.recoverpass" ,null, LocaleContextHolder.getLocale());
    	message+= "http://localhost:8080/EI1027NatureAdventure1/passwordRecoveryAuth/"+ token +".html";
    	message+= messageSource.getMessage("mail.recoverpass2" ,null, LocaleContextHolder.getLocale());
    	String subject = messageSource.getMessage("mail.recoverpasssubject" ,null, LocaleContextHolder.getLocale());
    	String to = email.getTo();
    	this.sendMessage(message, to, subject);
    }
    
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource= messageSource;
		
	}

}