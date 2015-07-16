package service;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import classes.Opinion;
import database.daoOpinion;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository
public class OpinionLayer {
	private daoOpinion daoOpinion;
	private int opinionID;
	
	public void addOpinion(Opinion opinion){
		this.opinionID++;
		opinion.setOpinionId(opinionID);
		daoOpinion.addElement(opinion);
	}
	
	public void deleteOpinion(int opinionid){
		daoOpinion.deleteElement(opinionid);
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

	public void setDaoOpinion(daoOpinion daoOpinion) {
		this.daoOpinion = daoOpinion;
	}
	
	public void inicializarNoteID(){
		try{
			this.opinionID = daoOpinion.getMaxID();
		} catch (Exception e) {
			this.opinionID = 0;
			System.out.println("noteID = 0");
		}
		
	}
	
	
}
