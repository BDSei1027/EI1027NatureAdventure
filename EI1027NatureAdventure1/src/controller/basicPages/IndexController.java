package controller.basicPages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.Client;
import controller.basics.AbstractController;

@Controller
public class IndexController extends AbstractController {	

	@RequestMapping(value="/index")
	public String indexPage(){
		return "index";
	}
}
