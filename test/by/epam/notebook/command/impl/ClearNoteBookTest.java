package by.epam.notebook.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import by.epam.notebook.bean.ClearNoteBookRequest;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.controller.Controller;
import by.epam.notebook.source.NoteBookProvider;


public class ClearNoteBookTest {
	
	private static final String COMMAND_NAME = "CLEAR_NOTEBOOK";
	private static final Controller CONTROLLER = new Controller();
	private static final NoteBookProvider  NOTEBOOK= NoteBookProvider.getInstance();
	
  @Test
  public void clearNoteBookTest() throws IOException {
	    List <Note> list =new ArrayList<Note> ();
	    list.add(new Note("one", "05.10.2016"));
	    list.add(new Note("two", "05.10.2016"));
	    NOTEBOOK.getNoteBook().setNotes(list);
	    ClearNoteBookRequest clearNoteBookRequest = new ClearNoteBookRequest();
		clearNoteBookRequest.setCommandName(COMMAND_NAME);
		Response clearNoteBookResponse = CONTROLLER.doRequest(clearNoteBookRequest);
		Assert.assertTrue(NOTEBOOK.getNoteBook().getNotes().isEmpty());
  }
}
