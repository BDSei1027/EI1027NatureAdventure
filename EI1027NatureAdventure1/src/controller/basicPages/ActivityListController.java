package controller.basicPages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import controller.basics.AbstractController;

@Controller
public class ActivityListController extends AbstractController {		
	
	@RequestMapping(value="/activities")
	public String activitiesPage(Model model){
		model.addAttribute("activityList", service.getAllActivities());
		return "activities";
	}	
}
