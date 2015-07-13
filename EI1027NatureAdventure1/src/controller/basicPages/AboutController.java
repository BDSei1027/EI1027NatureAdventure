package controller.basicPages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import controller.basics.AbstractController;

@Controller
public class AboutController extends AbstractController {		

	@RequestMapping(value="/about")
	public String aboutPage(){
		return "about";
	}
}
