package validators;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Booking;
import classes.User;

public class BookingValidator implements Validator {
	String language ="EN";
	
	public BookingValidator(HttpSession session) {
		super();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			language = user.getLanguage();
			if (language == null || language.equals("")) {
				language = "EN";
			}
		}
	}

	@Override
	public boolean supports(Class<?> cls) {
		return Booking.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Booking booking = (Booking) obj;
		
		if(booking.getGroupSize()<=0){
			if(language.equals("ES")){
				errors.rejectValue("groupSize", "Invalid value", "El grupo debe ser mayor que 0"); //El grupo debe ser mayor que 0	
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("groupSize", "Invalid value", "The group size must be greater than 0"); //El grupo debe ser mayor que 0	
			}
			
		}
		
		if(booking.getDateActivity()==null){
			if(language.equals("ES")){
				errors.rejectValue("dateActivity", "Invalid date", "Este campo debe tener algun valor");//Este campo debe tener algun valor
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("dateActivity", "Invalid date", "This field must have some value");//Este campo debe tener algun valor
			}
			
		}
		
		if(booking.getPrice()<=0){
			if(language.equals("ES")){
				errors.rejectValue("price", "negative value", "El precio debe ser mayor que 0");//El precio debe ser mayor que 0
			}else if(language.equals("EN") || language != null){
				errors.rejectValue("price", "negative value", "The price must be greater than 0");//El precio debe ser mayor que 0
			}
			
		}
	}

}
