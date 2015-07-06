package service;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;

import classes.Client;
import classes.ClientRegister;
import classes.User;
import database.daoClient;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository
public class ClientLayer {
	private daoClient daoClient;
	
	/*
	 * CLIENT ZONE
	 */
	
	/** Add a client in the database
	 * @param client
	 */
	public void addClient(Client client){
		daoClient.addElement(client);
		
	}
	
	/** Delete a client from the database
	 * @param client
	 */
	public void deleteClient(Client client){
		if(this.getClient(client.getClientId())==null) return;
		this.deleteClient(client.getClientId());
		
	}
	
	/** Delete a client in the database. The clientID is required
	 * @param clientID
	 */
	public void deleteClient(String clientID){
		daoClient.deleteElement(clientID);
	}
	
	/** Update a client in the database
	 * @param client
	 */
	public void updateClient(Client client){
		if (this.getClient(client.getClientId())==null) return;
		daoClient.updateElement(client);
	}
	
	/** Retrieve the desired Client. The clientID is required
	 * @param clientID
	 * @return
	 */
	public Client getClient(String clientID){
		Client myClient= (Client) daoClient.getElement(clientID);
		return myClient;
	}
	
	/** Retrieve the desired Client
	 * @param user
	 * @return The client
	 */
	public Client getClient(User user){
		return (Client) daoClient.getElement(user.getUser());
		
	}
	
	/** Get all the clients registered in the system
	 * @return All the clients
	 */
	public Collection<Client> getAllClients(){
		Map<String,Client> allClients =  (Map<String, Client>) daoClient.getElements();
		Collection<Client> allClientsClasses = allClients.values();
		return allClientsClasses;
		
	}
	
	/** Create a Client given a ClientRegister
	 * @param cl
	 * @return The desired Client
	 */
	public Client createClientFrom(ClientRegister cl) {
		Client client = new Client();
		client.setClientId(cl.getId());
		client.setClientName(cl.getName());
		client.setClientLastName(cl.getLastName());
		client.setClientEmail(cl.getEmail());
		return client;
	}

	public void setDaoClient(daoClient daoClient) {
		this.daoClient = daoClient;
	}
	

}
