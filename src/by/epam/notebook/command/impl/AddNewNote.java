package by.epam.notebook.command.impl;


import by.epam.notebook.bean.AddNewNoteRequest;
import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;

public class AddNewNote implements Command {
	@Override
	public Response execute(Request request) throws CommandException {

		AddNewNoteRequest req = null;

		if (request instanceof AddNewNoteRequest) {
			req = (AddNewNoteRequest) request;
		} else {
			throw new CommandException("WRONG REQUEST");
		}

		String note = req.getNote();
		Response response = new Response();
		NoteBookService service = ServiceFactory.getInstance().getNoteBookService();

		try {
			service.addNote(note);
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		response.setErrorStatus(false);
		response.setResultMessage("NOTE HAS BEEN ADDED");
		return response;
	}
}
