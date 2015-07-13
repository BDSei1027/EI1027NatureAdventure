package service;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import classes.Opinion;
import database.daoOpinion;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository
public class OpinionLayer {
	private daoOpinion daoOpinion;
	
	public void addOpinion(Opinion opinion){
		daoOpinion.addElement(opinion);
	}
	
	public void deleteOpinion(String clientid, int idAct){
		daoOpinion.deleteElement(clientid, idAct);
	}
	
	public void updateOpinion(Opinion opinion){
		daoOpinion.updateElement(opinion);
	}
	
	public Opinion getOpinion(String clientid, int idAct){
		return daoOpinion.getElement(clientid, idAct);
	} 
	
	public Collection<Opinion> getAllOpinions(){
		return daoOpinion.getElements();
	}
	
	public void deleteAllOpinionsFromActivity(int idAct){
		daoOpinion.deleteElementFromActivity(idAct);
	}
	
	public Collection<Opinion> getOpinionsFromActivity(int idAct){
		return daoOpinion.getOpinionsFromActivity(idAct);
	}
	
	public Collection<Opinion> getOpinionsFromClient(String clientID){
		return daoOpinion.getOpinionsFromClient(clientID);
	}

	public void setDaoOpinion(daoOpinion daoOpinion) {
		this.daoOpinion = daoOpinion;
	}
	
	
	
}
