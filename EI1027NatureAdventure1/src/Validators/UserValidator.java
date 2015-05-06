package Validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		if (user.getName().trim().equals("")){
			errors.rejectValue("nombre", "obligatorio",
                    "Es necesario introducir un valor");

		}
		
	}

}
