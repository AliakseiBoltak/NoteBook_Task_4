package by.epam.notebook.command;

import java.io.IOException;

import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.command.exception.CommandException;

public interface Command {
	
	Response execute(Request request) throws CommandException, IOException;
	
	
}
