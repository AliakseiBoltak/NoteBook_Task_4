package by.epam.notebook.command.impl;

import by.epam.notebook.bean.ClearNoteBookRequest;

import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;

public class ClearNoteBook implements Command {
	@Override
	public Response execute(Request request) throws CommandException {
		ClearNoteBookRequest req = null;

		if (request instanceof ClearNoteBookRequest) {
			req = (ClearNoteBookRequest) request;
		} else {
			throw new CommandException("INVALID REQUEST");
		}

		Response response = new Response();

		NoteBookService service = ServiceFactory.getInstance().getNoteBookService();

		try {
			service.clearNoteBook();
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		response.setErrorStatus(false);
		response.setResultMessage("SUCCESS");
		return response;
	}
}
