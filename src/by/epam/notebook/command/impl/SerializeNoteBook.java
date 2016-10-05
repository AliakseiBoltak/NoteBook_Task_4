package by.epam.notebook.command.impl;

import java.io.IOException;

import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.SerializeNoteBookRequest;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;

public class SerializeNoteBook implements Command {
	@Override
	public Response execute(Request request) throws CommandException, IOException {

		SerializeNoteBookRequest req = null;

		if (request instanceof SerializeNoteBookRequest) {
			req = (SerializeNoteBookRequest) request;
		} else {
			throw new CommandException("WRONG REQUEST");
		}

		Response response = new Response();

		String filePath = req.getFilePath();

		NoteBookService noteBookService = ServiceFactory.getInstance().getNoteBookService();

		try {
			noteBookService.serializeNoteBook(filePath);
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
