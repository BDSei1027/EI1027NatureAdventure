package validators;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Client;


public class ClientValidator implements Validator {
	MessageSource msgSrc; 
	
	public ClientValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Client.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Client client = (Client) obj;
		if(client.getClientName().trim().equals("")){
			errors.rejectValue("name", "nameContent",msgSrc.getMessage("validator.clientvalidator.name", null, LocaleContextHolder.getLocale()));
		}
		
		if (client.getClientLastName().trim().equals("")) {
			errors.rejectValue("lastName", "lastNameContent",
					msgSrc.getMessage("validator.clientvalidator.lastname", null, LocaleContextHolder.getLocale()));
		}

		if (!client.getClientEmail().matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.rejectValue("email", "emailAddress",
					msgSrc.getMessage("validator.clientvalidator.email", null, LocaleContextHolder.getLocale()));
		}
	}

	public MessageSource getMsgSrc() {
		return msgSrc;
	}

	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
