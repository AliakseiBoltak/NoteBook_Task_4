package by.epam.notebook.bean;
import java.util.List;

import by.epam.notebook.bean.entity.Note;

public class ShowNotesResponse extends Response  {
	
	
	 private List<Note> notes = null;

	 public ShowNotesResponse() {

	    } 
	 
	    public List<Note> getNotes() {
	        return notes;
	    }

	    public void setNotes(List<Note> notes) {
	        this.notes = notes;
	    }

}
