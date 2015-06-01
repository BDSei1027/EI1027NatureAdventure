package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.BookingActivity;

public class BookingActivityValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return BookingActivity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		BookingActivity myActivity = (BookingActivity) obj;
		if(myActivity.getId()<0){
			errors.rejectValue("id","valorNegativo","Hay un problema con el id");
		}
		if(myActivity.getName().equals("")){
			errors.rejectValue("name","nombreNulo","Este campo no puede ser nulo");
		}
		if(myActivity.getPlace().equals("")){
			errors.rejectValue("place","lugarNulo","Este campo no puede ser nulo");
		}
		if(myActivity.getGroupSize()<=0){
			errors.rejectValue("groupSize","valorNegativo","Este campo no puede ser menor o igual a cero");
		}
	}

}
