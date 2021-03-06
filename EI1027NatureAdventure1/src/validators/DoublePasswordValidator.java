package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.DoublePassword;
import classes.User;

public class DoublePasswordValidator  implements Validator {
	
	public DoublePasswordValidator() {
		super();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		DoublePassword dp = (DoublePassword) obj;
		if(dp.getPassword().length()<6){
			errors.rejectValue("password", "validator.doublepasswordvalidator.passwordlenght","La contraseņa debe contener mas de 6 caracteres");
		}
		
		if (!dp.getPassword().equals(dp.getConfirmation())) {
			errors.rejectValue("password", "validator.doublepasswordvalidator.password", "Contraseņa incorrecta");
		}
	
	}

}
