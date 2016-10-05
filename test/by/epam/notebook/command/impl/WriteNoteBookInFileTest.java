package by.epam.notebook.command.impl;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.WriteNoteBookInFileRequest;
import by.epam.notebook.controller.Controller;

public class WriteNoteBookInFileTest {
	
	private static final String VALID_PATH = "src/notebook.txt";
	private static final String INVALID_PATH = "src/notebook.t";
	private static final Controller CONTROLLER = new Controller();
	private static final String COMMAND_NAME = "WRITE_NOTEBOOK_IN_FILE";
	
  @Test
  public void writeInFileValid() throws IOException {
	        WriteNoteBookInFileRequest request = new WriteNoteBookInFileRequest();
			request.setCommandName(COMMAND_NAME);
			request.setFilePath(VALID_PATH );
			Response response = CONTROLLER .doRequest(request);
			Assert.assertFalse(response.isErrorStatus());
		}
  @Test
  public void writeInFileInValid() throws IOException {
	        WriteNoteBookInFileRequest request = new WriteNoteBookInFileRequest();
			request.setCommandName(COMMAND_NAME);
			request.setFilePath(INVALID_PATH );
			Response response = CONTROLLER .doRequest(request);
			Assert.assertTrue(response.isErrorStatus());
		}
  }


