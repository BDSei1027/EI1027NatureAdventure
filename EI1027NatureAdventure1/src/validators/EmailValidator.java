package validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Email;

public class EmailValidator implements Validator{
	@Autowired
	MessageSource msgSrc; 
	
	public EmailValidator() {
		super();
	}
	
	@Override
	public boolean supports(Class<?> cls) {
		return Email.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Email email = (Email) obj;
		
		if(email.getTo().equals("")){
			errors.rejectValue("to","destinatarioNulo",msgSrc.getMessage("validator.emailvalidator.to", null, LocaleContextHolder.getLocale()));
			
		}
	}

	public MessageSource getMsgSrc() {
		return msgSrc;
	}

	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
