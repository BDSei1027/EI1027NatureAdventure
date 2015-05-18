package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Instructor;
import classes.User;

public class InstructorValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Instructor instructor = (Instructor) obj;
		if(instructor.getSsNumber().length()<=9){
			errors.rejectValue("ssNumber","longitud","La longitud debe ser mayor que 9 carácteres"); 
		}
		if(instructor.getIdNumber().length()!=9){
			errors.rejectValue("idNumber", "digitos","La longitud debe ser de 9 carácteres");
		}
		
	}

}
