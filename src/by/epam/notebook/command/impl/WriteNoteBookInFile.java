package by.epam.notebook.command.impl;

import java.io.IOException;
import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.WriteNoteBookInFileRequest;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;

public class WriteNoteBookInFile implements Command {

	@Override
	public Response execute(Request request) throws CommandException, IOException {

		WriteNoteBookInFileRequest req = null;

		if (request instanceof WriteNoteBookInFileRequest) {
			req = (WriteNoteBookInFileRequest) request;
		} else {
			throw new CommandException("WRONG REQUEST");
		}

		Response response = new Response();
		String path = req.getFilePath();
		NoteBookService service = ServiceFactory.getInstance().getNoteBookService();

		try {
			service.writeNoteBookInFile(path);
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
