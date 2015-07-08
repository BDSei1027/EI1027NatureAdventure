package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.User;

public class UserValidator implements Validator { 
	
	public UserValidator() {
		super();
	}
	
	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		if (user.getUser().trim().equals("")){
			errors.rejectValue("name", "validator.uservalidator.name", "Es necesario introducir un valor");//Es necesario introducir un valor
		}
		
		if (user.getPassword().trim().equals("")){
			errors.rejectValue("password", "validator.uservalidator.password", "Este campo no puede quedar vacío");//Es necesario introducir un valor
		}
		
	}

}
