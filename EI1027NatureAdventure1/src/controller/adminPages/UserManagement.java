package controller.adminPages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.basics.AbstractController;


/**
 * Class that manages the login data (users)
 */
@Controller
@RequestMapping("/admin/userManagement")
public class UserManagement extends AbstractController {	

	/**
	 * Returns the main page for user management
	 * @param model Injected model
	 * @return UserManagement.jsp with the modified model
	 */
	@RequestMapping
	public String userPage(Model model){
		model.addAttribute("userList", service.getAllUsers());
		return "admin/userManagement";
	}
	
	/**
	 * Returns the main page after an action
	 * @param rezCode The description of the result of the action
	 * @param idUser The user affected
	 * @param model Injected model
	 * @return UserManagement.jsp with the modified model and the result code
	 */
	@RequestMapping("/{rezCode}&{idUser}")
	public String userPage(@PathVariable int rezCode, @PathVariable String idUser, Model model){
		
		model.addAttribute("error", rezCode);
		model.addAttribute("id",idUser);
		model.addAttribute("userList", service.getAllUsers());
		return "admin/userManagement";
	}
	
	/**
	 * Method that deletes the user {userNamen}
	 * @param userName The username
	 * @param model Injected model
	 * @return Redirect to the main page
	 */
	@RequestMapping(value="/remove/{userName}")
	public String removeUser(@PathVariable String userName, Model model){
		service.deleteUser(userName);
		return "forward:/admin/userManagement/"+RESULT_DELETE_OR_DENY+"&"+userName+".html";
	}
}
