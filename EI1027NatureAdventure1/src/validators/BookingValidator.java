package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Booking;

public class BookingValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Booking.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Booking booking = (Booking) obj;
		
		if(booking.getGroupSize()<=0){
			errors.rejectValue("groupSize", "Invalid value", "The group size must be greater than 0"); //El grupo debe ser mayor que 0	
		}
		
		if(booking.getDateActivity()==null){
			errors.rejectValue("dateActivity", "Invalid date", "This field must have some value");//Este campo debe tener algun valor
		}
		
		if(booking.getPrice()<=0){
			errors.rejectValue("price", "negative value", "The price must be greater than 0");//El precio debe ser mayor que 0
		}
	}

}
