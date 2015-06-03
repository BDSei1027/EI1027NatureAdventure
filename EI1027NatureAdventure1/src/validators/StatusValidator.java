package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Status;

public class StatusValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Status.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Status status = (Status) obj;
		if(status.getSsNumber().trim().length() <= 9){
			errors.rejectValue("ssNumber","longitud","The length must be greater than 9 characters");// La longitud debe ser mayor que 9 caracteres
		}
		if(status.getStatus().equals("")){
			errors.rejectValue("status","status","This field cannot be null");//Este campo no puede estar vacío
		}
	}

}
