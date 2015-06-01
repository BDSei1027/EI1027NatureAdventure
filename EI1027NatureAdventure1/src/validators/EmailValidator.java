package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Email;

public class EmailValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return Email.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Email email = (Email) obj;
		if(email.getTo().equals("")){
			errors.rejectValue("to","destinatarioNulo","Este campo no puede ser nulo");
		}
	}

}
