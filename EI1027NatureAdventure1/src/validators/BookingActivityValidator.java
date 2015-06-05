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
		if(myActivity.getIdBooking()<0){
			errors.rejectValue("id","valorNegativo","There is a problem with the id");//Hay un problema con el id
		}
		if(myActivity.getNameActivity().equals("")){
			errors.rejectValue("name","nombreNulo","This field cannot be null");//Este campo no puede ser nulo
		}
		if(myActivity.getPlace().equals("")){
			errors.rejectValue("place","lugarNulo","This field cannot be null");//Este campo no puede ser nulo
		}
		if(myActivity.getGroupSize()<=0){
			errors.rejectValue("groupSize","valorNegativo","This field cannot be lower or equal than 0");//Este campo no puede ser menor o igual a cero
		}
		if(myActivity.getLevel().equals("")){
			errors.rejectValue("groupSize","valorNegativo","This field cannot be lower or equal than 0");//Este campo no puede ser menor o igual a cero
		}
		if(myActivity.getPrice()<=0){
			errors.rejectValue("price", "negative value", "The price must be greater than 0"); //El precio debe ser mayor que 0
		}
	}

}
