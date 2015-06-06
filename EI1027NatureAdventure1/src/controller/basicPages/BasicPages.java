package controller.basicPages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


@Controller
public class BasicPages extends AbstractController {	

	@RequestMapping(value="/index")
	public String indexPage(){
		return "index";
	}
	
	@RequestMapping(value="/activities")
	public String activitiesPage(){
		return "activities";
	}
}
