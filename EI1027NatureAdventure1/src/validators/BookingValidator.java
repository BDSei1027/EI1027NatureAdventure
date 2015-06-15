package validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Booking;


public class BookingValidator implements Validator {
	@Autowired
	MessageSource msgSrc;
	
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
			errors.rejectValue("groupSize", "Invalid value",msgSrc.getMessage("validator.bookingvalidator.groupsize", null, LocaleContextHolder.getLocale()));
		}
		
		if(booking.getDateActivity()==null){
			errors.rejectValue("dateActivity", "Invalid date", msgSrc.getMessage("validator.bookingvalidator.dateactivity", null, LocaleContextHolder.getLocale()));
		}
		
		if(booking.getPrice()<=0){
			errors.rejectValue("price", "negative value", msgSrc.getMessage("validator.bookingvalidator.price", null, LocaleContextHolder.getLocale()));
		}
	}

	public MessageSource getMsgSrc() {
		return msgSrc;
	}

	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
