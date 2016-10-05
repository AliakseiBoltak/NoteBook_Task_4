package by.epam.notebook.controller;

import java.util.HashMap;
import java.util.Map;
import by.epam.notebook.command.Command;
import by.epam.notebook.command.impl.AddNewNote;
import by.epam.notebook.command.impl.ClearNoteBook;
import by.epam.notebook.command.impl.DeserializeNoteBook;
import by.epam.notebook.command.impl.FindNotesByContent;
import by.epam.notebook.command.impl.FindNotesByDate;
import by.epam.notebook.command.impl.ReadNoteBookFromFile;
import by.epam.notebook.command.impl.SerializeNoteBook;
import by.epam.notebook.command.impl.ShowAllNotes;
import by.epam.notebook.command.impl.WriteNoteBookInFile;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {
		commands.put("ADD_NEW_NOTE", new AddNewNote());
		commands.put("FIND_NOTES_BY_CONTENT", new FindNotesByContent());
		commands.put("FIND_NOTES_BY_DATE", new FindNotesByDate());
		commands.put("SHOW_ALL_NOTES", (Command) new ShowAllNotes());
		commands.put("CLEAR_NOTEBOOK", new ClearNoteBook());
		commands.put("WRITE_NOTEBOOK_IN_FILE", new WriteNoteBookInFile());
		commands.put("READ_NOTEBOOK_FROM_FILE", new ReadNoteBookFromFile());
		commands.put("SERIALIZE_NOTEBOOK", new SerializeNoteBook());
		commands.put("DESERIALIZE_NOTEBOOK", new DeserializeNoteBook());
	}

	public Command getCommand(String commandName) {
		Command command;

		command = commands.get(commandName);

		return command;
	}
}
