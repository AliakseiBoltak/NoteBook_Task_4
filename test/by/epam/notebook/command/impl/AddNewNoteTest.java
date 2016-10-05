package by.epam.notebook.command.impl;

import org.testng.annotations.Test;

import by.epam.notebook.bean.AddNewNoteRequest;
import by.epam.notebook.bean.Response;
import by.epam.notebook.controller.Controller;
import by.epam.notebook.source.NoteBookProvider;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.io.IOException;

public class AddNewNoteTest {
	
	
	private static final String COMMAND_NAME = "ADD_NEW_NOTE";
	private static final Controller CONTROLLER = new Controller();
	private static final NoteBookProvider  NOTEBOOK= NoteBookProvider.getInstance();

    @Test
    public void addNoteTest() throws IOException {
    	AddNewNoteRequest addNoteRequest = new AddNewNoteRequest();
		addNoteRequest.setCommandName(COMMAND_NAME);
		addNoteRequest.setNote("add");
		Response addNoteResponse = CONTROLLER.doRequest(addNoteRequest);
		Assert.assertFalse(NOTEBOOK.getNoteBook().getNotes().isEmpty());
    }
}
