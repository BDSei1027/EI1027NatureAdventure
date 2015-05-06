package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Instructor;

public class InstructorValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
