package by.epam.notebook.command.impl;

import org.testng.annotations.Test;
import by.epam.notebook.bean.ReadNoteBookFromFileRequest;
import by.epam.notebook.bean.Response;
import by.epam.notebook.controller.Controller;
import java.io.IOException;
import org.testng.Assert;

public class ReadNoteBookFromFileTest {

	private static final String VALID_PATH = "src/notebook.txt";
	private static final String INVALID_PATH = "src/notebook.t";
	private static final String COMMAND_NAME = "READ_NOTEBOOK_FROM_FILE";
	private static final Controller CONTROLLER = new Controller();
	
	@Test
	public void readFromFileValid() throws IOException {
	    ReadNoteBookFromFileRequest request = new ReadNoteBookFromFileRequest();
		request.setCommandName(COMMAND_NAME);
		request.setFilePath(VALID_PATH );
		Response response = CONTROLLER .doRequest(request);
		Assert.assertFalse(response.isErrorStatus());
	}
	
	@Test
	public void readFromFileInValid() throws IOException {
	    ReadNoteBookFromFileRequest request = new ReadNoteBookFromFileRequest();
		request.setCommandName(COMMAND_NAME);
		request.setFilePath(INVALID_PATH );
		Response response = CONTROLLER .doRequest(request);
		Assert.assertTrue(response.isErrorStatus());
	}
}
