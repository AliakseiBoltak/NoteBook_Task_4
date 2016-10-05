package by.epam.notebook.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.epam.notebook.bean.FindNotesByContentRequest;
import by.epam.notebook.bean.ShowNotesResponse;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.controller.Controller;
import by.epam.notebook.source.NoteBookProvider;

public class FindNotesByContentTest {
	
	private static final String COMMAND_NAME = "FIND_NOTES_BY_CONTENT";
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
  public void findNotesByContent() throws IOException {
	    FindNotesByContentRequest findNoteRequest = new FindNotesByContentRequest();
		findNoteRequest.setCommandName(COMMAND_NAME);
		findNoteRequest.setContent("add");
		ShowNotesResponse showNoteResponse = (ShowNotesResponse)  CONTROLLER.doRequest(findNoteRequest);
		Assert.assertTrue(showNoteResponse.getNotes().size()==1);
  }
}
