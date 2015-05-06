package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Activity;

public class ActivityValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return Activity.class.equals(cls);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
