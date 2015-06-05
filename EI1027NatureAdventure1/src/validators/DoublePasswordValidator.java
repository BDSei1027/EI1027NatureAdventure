package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.DoublePassword;
import classes.User;

public class DoublePasswordValidator  implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		DoublePassword dp = (DoublePassword) obj;
		
		if (!dp.getPassword().equals(dp.getConfirmation())) {
			errors.rejectValue("password", "obligatorio", "The password must be the same");
		}
		//TODO Implementar
		
	}

}
