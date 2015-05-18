package validators;

import javax.servlet.http.HttpSession;

import classes.User;

public class SessionValidator {

	private HttpSession session;

	public SessionValidator(HttpSession session) {
		this.session = session;
	}

	public boolean isLogged() {
		User user = (User) session.getAttribute("user");
		if(user == null) return false;
		return true;
	}

	public boolean hasPermissions(int permissionLevel) {
		User user = (User) session.getAttribute("user");
		try{
			if(user.getType() == permissionLevel) return true;
			return false;
			
			//This means that the user is not logged
		} catch (NullPointerException e){
			return false;
		}
	}

}
