package by.epam.notebook.bean.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteBook implements Serializable {
	
	List<Note> notes;
	
	public NoteBook() {
		notes = new ArrayList<>();
	}
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}


	public void add(Note newNote) {
		this.notes.add(newNote);   
	}

	public void clear() {
		if(this.notes.size() != 0) {
			this.notes.clear();
		}
	}
	
	

	@Override
	public String toString() {
		return "NoteBook [notes=" + notes + "]";
	}
}
