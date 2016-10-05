package by.epam.notebook.command.impl;
import java.util.ArrayList;

import by.epam.notebook.bean.FindNotesByContentRequest;
import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.ShowNotesResponse;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;
import by.epam.notebook.source.NoteBookProvider;

public class FindNotesByContent implements Command {


	@Override
	public Response execute(Request request) throws CommandException {

		FindNotesByContentRequest req = null;

		if (request instanceof FindNotesByContentRequest) {
			req = (FindNotesByContentRequest) request;
		} else {
			throw new CommandException("INVALID COMMAND");
		}

		String content = req.getContent();
		ShowNotesResponse response = new ShowNotesResponse();
		NoteBookService noteBookService = ServiceFactory.getInstance().getNoteBookService();
		ArrayList<Note> result = new ArrayList<Note>();
		
		 try {
	            result = (ArrayList<Note>) noteBookService.findNotesByContent(content);
	        } catch (ServiceException e) {
	            response.setErrorStatus(true);
	            response.setErrorMessage(e.getMessage());
	            return response;
	        }

	        response.setNotes(result);
	        response.setErrorStatus(false);
	        response.setResultMessage("Success!");

	        return response;
	}
}
