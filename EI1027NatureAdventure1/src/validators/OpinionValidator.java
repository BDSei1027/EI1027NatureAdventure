package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import classes.Opinion;

public class OpinionValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Opinion.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Opinion opinion = (Opinion) obj;
		if(opinion.getAuthor().trim().equals("")){
			errors.rejectValue("author","validator.opinionvalidator.clientfullname","El nombre del cliente no puede estar vac�o");
		}
		if(opinion.getIdAct()<0){
			errors.rejectValue("idAct","validator.opinionvalidator.idact","El id de la actividad no puede ser nulo");
		}
		if(opinion.getOpinion().trim().equals("")){
			errors.rejectValue("opinion","validator.opinionvalidator.opinion","El contenido de la opini�n no puede estar vac�a");
		}
	}

}
