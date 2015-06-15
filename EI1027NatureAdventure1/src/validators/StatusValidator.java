package validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Status;

public class StatusValidator implements Validator { 
	@Autowired
	MessageSource msgSrc; 
	
	public StatusValidator() {
		super();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return Status.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Status status = (Status) obj;

		if(status.getSsNumber().trim().length() <= 9){
			errors.rejectValue("ssNumber","longitud",msgSrc.getMessage("validator.statusvalidator.ssnumber", null, LocaleContextHolder.getLocale()));
		}
		if(status.getStatus().equals("")){
			errors.rejectValue("status","status",msgSrc.getMessage("validator.statusvalidator.status", null, LocaleContextHolder.getLocale()));//Este campo no puede estar vacío
		}
	}
	public MessageSource getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
