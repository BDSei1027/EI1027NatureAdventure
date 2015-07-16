package service;

import java.util.Collection;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Repository;

import classes.ClientRegister;
import classes.Instructor;
import classes.User;
import database.daoUser;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository
public class UserLayer {
	
	private daoUser daoUser;
	
	// Encriptacion
	private PasswordEncryptor encryptor = new BasicPasswordEncryptor();
	/*
	 * USER ZONE
	 */
	
	/** Login a user in the system
	 * @param user
	 * @param password
	 * @return The user data 
	 */
	public User loginUser(String user, String password) {
		User u = (User) daoUser.getElement(user.trim());
		if ( u == null ) return null;
		if ( encryptor.checkPassword(password, u.getPassword()) ) {
			u.clearPassword();
			return u;
		}
		else return null; // Bad Login
	}
	
	/** Add a user in the database, this self encrypt the password
	 * @param user
	 */
	public void addUser(User user){
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		daoUser.addElement(user);
		
	}
	
	/** Delete a user from the database
	 * @param identifier
	 */
	public void deleteUser(String identifier){
		User myUser = this.getUser(identifier);
		if (myUser==null) return;
		daoUser.deleteElement(myUser);	
		
	}
	
	/** Update an user in the database, does not update the password or type, for this <b>updateUserWithPasswordType</b>
	 * @param user
	 */
	public void updateUser(User user){
		if (user.getUser() == null) return;
		daoUser.updateElementWithoutPassword(user);
	}
	
	/**
	 * Update an user in the database, updates the password and the type. <b><i>The password must be plain text with encrypt</i></b>
	 * @param user
	 */
	public void updateUserWithPasswordType(User user) {
		if (user.getUser() == null) return;
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		daoUser.updateElement(user);
	}
	
	/** Retrieve the desired user form the database. The identifier is required
	 * @param identifier
	 * @return
	 */
	public User getUser(String identifier){
		User myUser = (User) daoUser.getElement(identifier);
		return myUser;
		
	}
	
	/** Retrieve all the users from the database
	 * @return
	 */
	public Collection<User> getAllUsers(){
		Map<String,User> allUsers =  (Map<String, User>) daoUser.getElements();
		Collection<User> allUsersClasses = allUsers.values();
		return allUsersClasses;
		
	}
	
	/** Create an User which represents a instructor given
	 * @param instructor
	 * @return A User which represents a instructor
	 */
	public User createUserFrom(Instructor instructor) {
		User newUser = new User();
		newUser.setUser(instructor.getIdNumber());
		newUser.setPassword(instructor.getTelephone());
		newUser.setName(instructor.getName());
		newUser.setLanguage("EN");
		newUser.setType(1);
		return newUser;
	}
	
	/** Create a user given a ClientRegister
	 * @param cl
	 * @return The desired user
	 */
	public User createUserFrom(ClientRegister cl) {
		User newUser = new User();
		newUser.setUser(cl.getId());
		newUser.setPassword(cl.getPassword());
		newUser.setName(cl.getName());
		newUser.setLanguage(cl.getLanguage());
		newUser.setType(2);
		return newUser;
	}

	/** Get the User count of the system
	 * @return The number of User registered
	 */
	public Integer getUserCount(){
		return daoUser.getUserCount();
	}
	
	public User getUserGivenAToken(String token){
		User user;
		try{
			user = daoUser.getUserGivenAToken(token);
			return user;
		}catch (Exception e){
			return null;
		}
	}

	public void setDaoUser(daoUser daoUser) {
		this.daoUser = daoUser;
	}

	public void setEncryptor(PasswordEncryptor encryptor) {
		this.encryptor = encryptor;
	}
	
}
