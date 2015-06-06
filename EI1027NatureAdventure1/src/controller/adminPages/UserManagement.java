package controller.adminPages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


@Controller
@RequestMapping("/admin/userManagement")
public class UserManagement extends AbstractController {	
	
//TODO Parm: "error":integer = 2 delete
//TODO Parm: "id"   :String  = user id

	@RequestMapping
	public String userPage(Model model){
		model.addAttribute("userList", service.getAllUsers());
		return "admin/userManagement";
	}
	
	@RequestMapping("/{rezCode}&{idUser}")
	public String userPage(@PathVariable int rezCode, @PathVariable String idUser, Model model){
		
		model.addAttribute("error", rezCode);
		model.addAttribute("id",idUser);
		model.addAttribute("userList", service.getAllUsers());
		return "admin/userManagement";
	}
	
	@RequestMapping(value="/remove/{userName}")
	public String removeUser(@PathVariable String userName, Model model){
		service.deleteUser(userName);
		return "forward:/admin/userManagement/"+2+"&"+userName+".html";
	}
}
