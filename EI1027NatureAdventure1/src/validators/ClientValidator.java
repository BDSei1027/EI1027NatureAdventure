package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Client;

public class ClientValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Client.class.equals(cls);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
