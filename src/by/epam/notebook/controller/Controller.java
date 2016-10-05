package by.epam.notebook.controller;

import java.io.IOException;

import by.epam.notebook.bean.Request;
import by.epam.notebook.bean.Response;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.exception.CommandException;

public class Controller {
	private CommandHelper helper = new CommandHelper();
	
	
	public Controller(){}
	
	public Response doRequest(Request request) throws IOException{
		
		String commandName = request.getCommandName();
		
		Command command = helper.getCommand(commandName);
		
		Response response;
		
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			response = new Response();
			response.setErrorStatus(true);
			response.setErrorMessage("ERROR!");
		}
		
		return response;
		
	}
}

