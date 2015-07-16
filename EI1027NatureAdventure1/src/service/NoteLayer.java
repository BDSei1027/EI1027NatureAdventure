package service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import classes.Note;
import database.daoNote;

@SuppressWarnings(value = {"unchecked", "unused"})
@Repository
public class NoteLayer {
	private daoNote daoNote;
	private int noteID;
	
	public void addNote(Note note){
		this.noteID ++;
		note.setId(noteID);
		daoNote.addElement(note);
	}
	
	public void deleteNote(int id){
		daoNote.deleteElement(id);
	}
	
	public void updateNote(Note note){
		daoNote.updateElement(note);
	}
	
	public Note getNote(int id){
		return daoNote.getElement(id);
	} 
	
	public Collection<Note> getAllNotes(){
		List<Note> myList = daoNote.getElements();
		return myList;
	}

	public void setDaoNote(daoNote daoNote) {
		this.daoNote = daoNote;
	}
	
	public void inicializarNoteID(){
		try{
			this.noteID = daoNote.getMaxID();
		} catch (Exception e) {
			this.noteID = 0;
			System.out.println("noteID = 0");
		}
		
	}
	
}
