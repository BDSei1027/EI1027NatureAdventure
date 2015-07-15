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
		if(opinion.getClientFullName().trim().equals("")){
			errors.rejectValue("clientFullName","validator.opinionvalidator.clientfullname","El nombre del cliente no puede estar vacío");
		}
		if(opinion.getClientId().trim().equals("")){
			errors.rejectValue("clientId","validator.opinionvalidator.clientid","El id del cliente no puede ser nulo");
		}
		if(opinion.getIdAct()<0){
			errors.rejectValue("idAct","validator.opinionvalidator.idact","El id de la actividad no puede ser nulo");
		}
		if(opinion.getOpinion().trim().equals("")){
			errors.rejectValue("opinion","validator.opinionvalidator.opinion","El contenido de la opinión no puede estar vacía");
		}
		if(opinion.getScore()<0){
			errors.rejectValue("score","validator.opinionvalidator.score","La puntuación no puede ser negativa");
		}
	}

}
