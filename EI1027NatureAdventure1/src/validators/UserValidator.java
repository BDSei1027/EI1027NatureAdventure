package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		if (user.getUser().trim().equals("")){
			errors.rejectValue("nombre", "obligatorio",
                    "A value must be introduced");//Es necesario introducir un valor

		}
		
	}

}
