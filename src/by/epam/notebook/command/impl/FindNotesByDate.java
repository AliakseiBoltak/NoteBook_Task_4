package by.epam.notebook.command.impl;

import java.util.ArrayList;

import by.epam.notebook.bean.FindNotesByDateRequest;
import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.ShowNotesResponse;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.bean.entity.NoteBook;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.ServiceFactory;
import by.epam.notebook.service.exception.ServiceException;
import by.epam.notebook.source.NoteBookProvider;

public class FindNotesByDate implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		FindNotesByDateRequest req = null;

		if (request instanceof FindNotesByDateRequest) {
			req = (FindNotesByDateRequest) request;
		} else {
			throw new CommandException("WRONG REQUEST");
		}

		ShowNotesResponse response = new ShowNotesResponse();

		ArrayList<Note> result = new ArrayList<Note>();

		String date = req.getDate();
		
		NoteBookService noteBookService = ServiceFactory.getInstance().getNoteBookService();

        try {
            result = (ArrayList<Note>) noteBookService.findNotesByDate(date);
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
