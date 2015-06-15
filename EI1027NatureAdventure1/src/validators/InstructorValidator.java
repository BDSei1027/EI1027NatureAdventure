package validators;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aeat.valida.Validador;

import classes.Instructor;


public class InstructorValidator implements Validator {
	MessageSource msgSrc; 
	
	public InstructorValidator() {
		super();
	}
	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Instructor instructor = (Instructor) obj;

		if(instructor.getSsNumber().trim().length() <= 9){
			errors.rejectValue("ssNumber","longitud",msgSrc.getMessage("validator.instructorvalidator.ssnumber", null, LocaleContextHolder.getLocale()));
		}
		
		if(instructor.getName().trim().equals("")){
			errors.rejectValue("name", "nameContent",msgSrc.getMessage("validator.instructorvalidator.name", null, LocaleContextHolder.getLocale()));
		}
		
		if(instructor.getLastName().trim().equals("")){
			errors.rejectValue("lastName", "lastNameContent",msgSrc.getMessage("validator.instructorvalidator.lastname", null, LocaleContextHolder.getLocale()));
		}
		
		if(!instructor.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			errors.rejectValue("email", "emailAddress",msgSrc.getMessage("validator.instructorvalidator.email", null, LocaleContextHolder.getLocale()));
		}
		Validador validadorAgenciaTributaria = new Validador();
		if(validadorAgenciaTributaria.checkNif(instructor.getIdNumber().toUpperCase())<0){
			errors.rejectValue("idNumber", "digitos",msgSrc.getMessage("validator.instructorvalidator.idnumber", null, LocaleContextHolder.getLocale()));
		}
	}
	public MessageSource getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
	}

}
