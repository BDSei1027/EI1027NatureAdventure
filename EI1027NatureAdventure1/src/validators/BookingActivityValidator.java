package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.BookingActivity;


public class BookingActivityValidator implements Validator{
	
	public BookingActivityValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return BookingActivity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		BookingActivity myActivity = (BookingActivity) obj;
		if(myActivity.getIdBooking()<0){
			errors.rejectValue("id","validator.bookingactivityvalidator.id","Hay un problema con el id");//Hay un problema con el id
		}
		if(myActivity.getNameActivity().equals("")){
			errors.rejectValue("name","validator.bookingactivityvalidator.name","Este campo no puede ser nulo");//Este campo no puede ser nulo
		}
		if(myActivity.getPlace().equals("")){
			errors.rejectValue("place","validator.bookingactivityvalidator.place","Este campo no puede ser nulo");//Este campo no puede ser nulo
		}
		if(myActivity.getGroupSize()<=0){
			errors.rejectValue("groupSize","validator.bookingactivityvalidator.groupsize","Este campo no puede ser menor o igual a cero");//Este campo no puede ser menor o igual a cero
		}
		if(myActivity.getLevel() <= 0){
			errors.rejectValue("groupSize","validator.bookingactivityvalidator.level","Este campo no puede ser menor o igual a cero");//Este campo no puede ser menor o igual a cero
		}
		if(myActivity.getPrice()<=0){
			errors.rejectValue("price", "validator.bookingactivityvalidator.price", "El precio debe ser mayor que 0"); //El precio debe ser mayor que 0
		}
	}

}
