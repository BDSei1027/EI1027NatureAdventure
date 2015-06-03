package controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/userManagement")
public class UserManagement extends AbstractController {	
	

	@RequestMapping
	public String userPage(Model model){
		model.addAttribute("userList", service.getAllUsers());
		return "admin/userManagement";
	}
	
	@RequestMapping(value="delete/{userName}")
	public String removeUser(@PathVariable String userName, Model model){
		service.deleteUser(userName);
		return "redirect:/admin/userManagement";
	}
}
