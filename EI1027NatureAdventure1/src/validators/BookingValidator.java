package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Booking;


public class BookingValidator implements Validator {
	
	public BookingValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Booking.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Booking booking = (Booking) obj;

		if(booking.getGroupSize()<=0){
			errors.rejectValue("groupSize", "validator.bookingvalidator.groupsize", "El grupo debe ser mayor que 0"); //El grupo debe ser mayor que 0	
		}
		
		if(booking.getDateActivity()==null){
				errors.rejectValue("dateActivity", "validator.bookingvalidator.dateactivity", "Este campo debe tener algun valor");//Este campo debe tener algun valor
		}
		
		if(booking.getPrice()<=0){
				errors.rejectValue("price", "validator.bookingvalidator.price", "El precio debe ser mayor que 0");//El precio debe ser mayor que 0
		}
	}
}
