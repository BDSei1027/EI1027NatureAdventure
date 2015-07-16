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
import classes.User;
import controller.basics.AbstractController;


@Controller
public class ActivityDetailsController extends AbstractController {		
	
	@RequestMapping(value="/activities/details/{idAct}")
	public String activityDetails(@PathVariable int idAct, Model model, HttpSession session){
		Opinion opinion = new Opinion();
		opinion.setIdAct(idAct);
		User user = (User) session.getAttribute("user");
		if(user != null) {
			opinion.setClientId(user.getUser());
			opinion.setDate(new Date());
			opinion.setClientFullName(user.getName());
		}
		
		model.addAttribute("activity", service.getActivity(idAct));
		model.addAttribute("opinion",  opinion);
		return "activityDetails";
	}
	
	@RequestMapping(value="/activity/idact/newopinion", method=RequestMethod.POST)
	public String postOpinion(@ModelAttribute("opinion") Opinion opinion, BindingResult bindingResult){
		new OpinionValidator().validate(opinion,bindingResult);
		if(bindingResult.hasErrors()) return "activityDetails";
		
		service.addOpinion(opinion);
		
		return "complete";
	}
}
	
