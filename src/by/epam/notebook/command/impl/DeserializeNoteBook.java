package by.epam.notebook.command.impl;


import by.epam.notebook.bean.DeserializeNoteBookRequest;
import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;


public class DeserializeNoteBook implements Command {

	 @Override
	    public Response execute(Request request) throws CommandException {
	        DeserializeNoteBookRequest req = null;

	        if(request instanceof DeserializeNoteBookRequest) {
	            req = (DeserializeNoteBookRequest) request;
	        } else {
	            throw new CommandException("WRONG REQUEST");
	        }

	        Response response = new Response();

	        String filePath = req.getFilePath();

	        NoteBookService noteBookService = ServiceFactory.getInstance().getNoteBookService();

	        try {
	            noteBookService.deserializeNoteBook(filePath);
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
