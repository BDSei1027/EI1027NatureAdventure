package validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.BookingActivity;


public class BookingActivityValidator implements Validator{
	@Autowired
	MessageSource msgSrc;
	
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
			errors.rejectValue("id","valorNegativo",msgSrc.getMessage("validator.bookingactivityvalidator.id", null, LocaleContextHolder.getLocale()));
		}
		if(myActivity.getNameActivity().equals("")){
			errors.rejectValue("name","nombreNulo","validator.bookingactivityvalidator.name");
		}
		if(myActivity.getPlace().equals("")){
			errors.rejectValue("place","lugarNulo",msgSrc.getMessage("validator.bookingactivityvalidator.place", null, LocaleContextHolder.getLocale()));
		}
		if(myActivity.getGroupSize()<=0){
			errors.rejectValue("groupSize","valorNegativo",msgSrc.getMessage("validator.bookingactivityvalidator.groupsize", null, LocaleContextHolder.getLocale()));
		}
		if(myActivity.getLevel() <= 0){
			errors.rejectValue("level","valorNegativo",msgSrc.getMessage("validator.bookingactivityvalidator.level", null, LocaleContextHolder.getLocale()));
		}
		if(myActivity.getPrice()<=0){
			errors.rejectValue("price", "negative value", msgSrc.getMessage("validator.bookingactivityvalidator.price", null, LocaleContextHolder.getLocale()));
		}
	}

	public MessageSource getMsgSrc() {
		return msgSrc;
	}

	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
