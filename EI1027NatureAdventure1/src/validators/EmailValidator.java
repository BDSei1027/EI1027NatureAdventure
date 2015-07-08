package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Email;

public class EmailValidator implements Validator{
	
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
			errors.rejectValue("to","validator.emailvalidator.to","Este campo no puede ser nulo");//Este campo no puede ser nulo
		}
	}

}
