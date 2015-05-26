package classes;

import org.springframework.stereotype.Component;

@Component
public class User {
	
	private String user;
	private String password;
	private String name;
	private int type = -1;
	private String language;

	public User() {
		super();
	}
	
	public User(User user) {
		super();
		this.setUser(user.getUser());
		this.setPassword(user.getPassword());
		this.setType(user.getType());
		this.setLanguage(user.getLanguage());
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String lng) {
		this.language = lng;
	}
	
	public void clearPassword() {
		this.password = null;
	}
}
