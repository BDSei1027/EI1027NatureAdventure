package controller.basicPages;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validators.OpinionValidator;
import classes.Opinion;
import controller.basics.AbstractController;


@Controller
public class ActivityDetailsController extends AbstractController {		
	
	@RequestMapping(value="/activities/details/{idAct}")
	public String activityDetails(@PathVariable int idAct, Model model, HttpSession session){
		Opinion opinion = new Opinion();
		opinion.setDate(new Date());

		model.addAttribute("activity", service.getActivity(idAct));
		model.addAttribute("opinions", service.getOpinionsFromActivity(idAct));
		model.addAttribute("opinion",  opinion);
		return "activityDetails";
	}
	
	@RequestMapping(value="/activity/{idAct}/newOpinion", method=RequestMethod.POST)
	public String postOpinion(@PathVariable int idAct, HttpSession session, @ModelAttribute("opinion") Opinion opinion, BindingResult bindingResult){
		opinion.setIdAct(idAct);
		
		new OpinionValidator().validate(opinion,bindingResult);
		if(bindingResult.hasErrors()) return "activityDetails";
		
		service.addOpinion(opinion);
		
		return "complete";
	}
}
	
