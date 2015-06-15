package validators;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.ClientRegister;

public class ClientRegisterValidator implements Validator{
	MessageSource msgSrc; 
	
	public ClientRegisterValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return ClientRegister.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ClientRegister client = (ClientRegister) obj;
		if (client.getName().trim().equals("")){
			errors.rejectValue("name", "nameContent",msgSrc.getMessage("validator.clientregistervalidator.name", null, LocaleContextHolder.getLocale()));
		}
		if(client.getLastName().trim().equals("")){
			errors.rejectValue("lastName", "lastNameContent",msgSrc.getMessage("validator.clientregistervalidator.lastname", null, LocaleContextHolder.getLocale()));
		}
		if(!client.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			errors.rejectValue("email", "emailAddress",msgSrc.getMessage("validator.clientregistervalidator.email", null, LocaleContextHolder.getLocale()));
		}
		if(client.getId().trim().length()!=9){
			errors.rejectValue("id", "digitos",msgSrc.getMessage("validator.clientregistervalidator.id", null, LocaleContextHolder.getLocale()));
		}
		if(!client.isTocs()){
			errors.rejectValue("tocs", "tocsContent",msgSrc.getMessage("validator.clientregistervalidator.tocs", null, LocaleContextHolder.getLocale()));
		}
		if(client.getLanguage().trim().equals("")){
			errors.rejectValue("language", "languageChoice",msgSrc.getMessage("validator.clientregistervalidator.language", null, LocaleContextHolder.getLocale()));
		}
		if(client.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "digitos",msgSrc.getMessage("validator.clientregistervalidator.password01", null, LocaleContextHolder.getLocale()));
		} else if(client.getPassword().trim().length()< 8) {
			errors.rejectValue("password", "digitos",msgSrc.getMessage("validator.clientregistervalidator.password02", null, LocaleContextHolder.getLocale()));
		}
	}

	public MessageSource getMsgSrc() {
		return msgSrc;
	}

	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
