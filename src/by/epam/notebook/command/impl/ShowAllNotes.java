package by.epam.notebook.command.impl;

import java.util.List;
import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.ShowNotesRequest;
import by.epam.notebook.bean.ShowNotesResponse;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;


public class ShowAllNotes implements Command {
	
	@Override
	public Response execute(Request request) throws CommandException {
		
		ShowNotesRequest req = null;

		if (request instanceof ShowNotesRequest) {
			req = (ShowNotesRequest) request;
		} else {
			throw new CommandException("ILLEGAL COMMAND");
		}

		ShowNotesResponse response = new ShowNotesResponse();
		List<Note> result = null;

		NoteBookService noteBookService = ServiceFactory.getInstance().getNoteBookService();

		try {
			result = noteBookService.showAllNotes();
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		response.setNotes(result);
		response.setErrorStatus(false);
		response.setResultMessage("SUCCESS");

		return response;
	 
	    }
}
