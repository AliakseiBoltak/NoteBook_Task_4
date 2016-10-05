package by.epam.notebook.command.impl;

import org.testng.annotations.Test;

import by.epam.notebook.bean.FindNotesByDateRequest;
import by.epam.notebook.bean.ShowNotesResponse;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.controller.Controller;
import by.epam.notebook.source.NoteBookProvider;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FindNotesByDateTest {

	
	private static final String COMMAND_NAME = "FIND_NOTES_BY_DATE";
	private static final Controller CONTROLLER = new Controller();
	private static final NoteBookProvider  NOTEBOOK= NoteBookProvider.getInstance();
	
	@BeforeMethod
	public void beforeMethod() {
		 List <Note> list =new ArrayList<Note> ();
		    list.add(new Note("one", "05.10.2016"));
		    list.add(new Note("add", "05.10.2016"));
		    NOTEBOOK.getNoteBook().setNotes(list);
	}

	@Test
	public void findNotesByDate() throws IOException {
		FindNotesByDateRequest findNotesByDate = new FindNotesByDateRequest();
		findNotesByDate.setCommandName(COMMAND_NAME);
		findNotesByDate.setDate("05.10.2016");
		ShowNotesResponse showNotesByDateResponse = (ShowNotesResponse) CONTROLLER .doRequest(findNotesByDate);
		Assert.assertTrue(showNotesByDateResponse.getNotes().size()==2);
	}
}
