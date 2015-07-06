package service;

import org.springframework.stereotype.Repository;

import classes.Token;
import database.daoSessionToken;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository
public class TokenLayer {
	private daoSessionToken daoToken;
	
	
	/*
	 * TOKEN ZONE
	 */
	
	/** Set a token to an User
	 * @param userName
	 * @param tokenString
	 */
	public void setToken(String userName, String tokenString) {
		Token token = new Token(); token.setUser(userName); token.setToken(tokenString);
		try {
			daoToken.addElement(token);
		} catch (Exception e) {
			daoToken.updateElement(token);
		}

	}
	
	/** Validate a token of an user
	 * @param userName
	 * @param tokenString
	 * @return A boolean value which represents its validation
	 */
	public boolean validateToken(String userName, String tokenString) {
		Token token = (Token) daoToken.getElement(userName);
		
		if (token == null) return false;
		if (token.getToken().equals(tokenString)) return true;
		return false;
	}
	
	/** Delete a token of an user
	 * @param tokenUser
	 */
	public void deleteToken(String tokenUser) {
		daoToken.deleteElement(tokenUser);
	}

	public void setDaoToken(daoSessionToken daoToken) {
		this.daoToken = daoToken;
	}
	
	
}
