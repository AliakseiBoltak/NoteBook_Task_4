package by.epam.notebook.core;

import java.io.IOException;

import java.util.Scanner;
import by.epam.notebook.bean.AddNewNoteRequest;
import by.epam.notebook.bean.ClearNoteBookRequest;
import by.epam.notebook.bean.DeserializeNoteBookRequest;
import by.epam.notebook.bean.FindNotesByContentRequest;
import by.epam.notebook.bean.FindNotesByDateRequest;
import by.epam.notebook.bean.ReadNoteBookFromFileRequest;
import by.epam.notebook.bean.Response;
import by.epam.notebook.bean.SerializeNoteBookRequest;
import by.epam.notebook.bean.ShowNotesRequest;
import by.epam.notebook.bean.ShowNotesResponse;
import by.epam.notebook.bean.WriteNoteBookInFileRequest;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.controller.Controller;

public class WorkWithUser {

	public static void start() throws IOException {
		String commands = "--------1-ADD_NEW_NOTE\n" + "--------2-CLEAR_NOTEBOOK\n"
				+ "--------3-FIND_NOTES_BY_CONTENT\n" + "--------4-FIND_NOTES_BY_DATE\n"
				+ "--------5-READ_NOTEBOOK_FROM_FILE\n" + "--------6-WRITE_NOTEBOOK_IN_FILE\n"
				+ "--------7-SHOW_ALL_NOTES\n" + "--------8-SERIALIZE_NOTEBOOK\n" + "--------9-DESERIALIZE_NOTEBOOK\n"
				+ "--------0-EXIT";

		Controller controller = new Controller();

		boolean enter = true;

		while (enter) {
			Scanner sc = new Scanner(System.in);
			System.out.println(commands);
			System.out.println("--------ENTER COMMAND:");
			String userCommand = sc.nextLine();

			switch (userCommand) {

			case "0": {
				System.out.println("GOODBYE");
				enter = false;
			}
				break;

			case "1": {
				System.out.println("ENTER YOUR NOTE:");
				String userNote = sc.nextLine();
				AddNewNoteRequest addNoteRequest = new AddNewNoteRequest();
				addNoteRequest.setCommandName("ADD_NEW_NOTE");
				addNoteRequest.setNote(userNote);
				Response addNoteResponse = controller.doRequest(addNoteRequest);

				if (addNoteResponse.isErrorStatus() == false) {
					System.out.println(addNoteResponse.getResultMessage());
				} else {
					System.out.println(addNoteResponse.getErrorMessage());
				}
			}
				break;

			case "2":
				ClearNoteBookRequest clearNoteBookRequest = new ClearNoteBookRequest();
				clearNoteBookRequest.setCommandName("CLEAR_NOTEBOOK");
				Response clearNoteBookResponse = controller.doRequest(clearNoteBookRequest);
				if (clearNoteBookResponse.isErrorStatus() == false) {
					System.out.println(clearNoteBookResponse.getResultMessage());
				} else {
					System.out.println(clearNoteBookResponse.getErrorMessage());
				}
				break;

			case "3":
				System.out.println("ENTER TEXT: ");
				String txt = sc.nextLine();
				FindNotesByContentRequest findNoteRequest = new FindNotesByContentRequest();
				findNoteRequest.setCommandName("FIND_NOTES_BY_CONTENT");
				findNoteRequest.setContent(txt);
				ShowNotesResponse showNoteResponse = (ShowNotesResponse) controller.doRequest(findNoteRequest);

				if (showNoteResponse.isErrorStatus() == true) {
					System.out.println(showNoteResponse.getErrorMessage());
				} else {
					for (Note n : showNoteResponse.getNotes()) {
						System.out.println(n.getDate() + "  " + n.getNote());
					}
					System.out.println("NUMBER OF NOTES CONTAINING THIS TEXT: " + showNoteResponse.getNotes().size());
				}
				break;

			case "4":
				System.out.println("ENTER DATE IN FORMAT 'dd.mm.yyyy': ");
				String date = new Scanner(System.in).nextLine();

				FindNotesByDateRequest findNotesByDate = new FindNotesByDateRequest();

				findNotesByDate.setCommandName("FIND_NOTES_BY_DATE");
				findNotesByDate.setDate(date);
				ShowNotesResponse showNotesByDateResponse = (ShowNotesResponse) controller.doRequest(findNotesByDate);

				if (showNotesByDateResponse.isErrorStatus() == true) {
					System.out.println(showNotesByDateResponse.getErrorMessage());
				} else {
					for (Note n : showNotesByDateResponse.getNotes()) {
						System.out.println(n.getDate() + "  " + n.getNote());
					}
					System.out.println("NUMBER OF NOTES WITH THIS DATE: " + showNotesByDateResponse.getNotes().size());
				}
				break;

			case "5":
				ReadNoteBookFromFileRequest request = new ReadNoteBookFromFileRequest();
				request.setCommandName("READ_NOTEBOOK_FROM_FILE");
				// use file in project to quickly see output result of program
				String pathIn = "src/notebook.txt";
				request.setFilePath(pathIn);
				Response response = controller.doRequest(request);
				if (response.isErrorStatus() == true) {
					System.out.println(response.getErrorMessage());
				} else {
					System.out.println(response.getResultMessage());
				}

				break;

			case "6":
				WriteNoteBookInFileRequest writeNoteBookInFileRequest = new WriteNoteBookInFileRequest();
				writeNoteBookInFileRequest.setCommandName("WRITE_NOTEBOOK_IN_FILE");
				// use file in project to quickly see output result of program
				String pathOut = "src/notebook.txt";
				writeNoteBookInFileRequest.setFilePath(pathOut);
				Response writeNoteBookInFileResponse = controller.doRequest(writeNoteBookInFileRequest);
				if (writeNoteBookInFileResponse.isErrorStatus() == true) {
					System.out.println(writeNoteBookInFileResponse.getErrorMessage());
				} else {
					System.out.println(writeNoteBookInFileResponse.getResultMessage());
				}
				break;

			case "7":
				ShowNotesRequest showNotesRequest = new ShowNotesRequest();
				showNotesRequest.setCommandName("SHOW_ALL_NOTES");
				ShowNotesResponse showAllNotesResponse = (ShowNotesResponse) controller.doRequest(showNotesRequest);

				if (showAllNotesResponse.isErrorStatus() == false) {
					if (showAllNotesResponse.getNotes().size() == 0) {
						System.out.println("NOTEBOOK IS EMPTY");
					} else {
						for (Note n : showAllNotesResponse.getNotes()) {
							System.out.println(n.getDate() + "  " + n.getNote());
						}
					}
				} else {
					System.out.println(showAllNotesResponse.getErrorMessage());
				}
				break;

			case "8":
				// use file in project to quickly see output result of program
				String pathSerialize = "src/notebookSerialize.txt";
				SerializeNoteBookRequest serializeNoteBookRequest = new SerializeNoteBookRequest();
				serializeNoteBookRequest.setCommandName("SERIALIZE_NOTEBOOK");
				serializeNoteBookRequest.setFilePath(pathSerialize);
				Response serializeNoteBookResponse = controller.doRequest(serializeNoteBookRequest);
				if (serializeNoteBookResponse.isErrorStatus() == true) {
					System.out.println(serializeNoteBookResponse.getErrorMessage());
				} else {
					System.out.println(serializeNoteBookResponse.getResultMessage());
				}
				break;

			case "9":
				// use file in project to quickly see output result of program
				String pathOutSerialize = "src/notebookSerialize.txt";
				DeserializeNoteBookRequest deserializeNoteBookRequest = new DeserializeNoteBookRequest();
				deserializeNoteBookRequest.setCommandName("DESERIALIZE_NOTEBOOK");
				deserializeNoteBookRequest.setFilePath(pathOutSerialize);
				Response deserializeNoteBookResponse = controller.doRequest(deserializeNoteBookRequest);
				if (deserializeNoteBookResponse.isErrorStatus() == true) {
					System.out.println(deserializeNoteBookResponse.getErrorMessage());
				} else {
					System.out.println(deserializeNoteBookResponse.getResultMessage());
				}
				break;

			default:
				System.out.println("ILLEGAL COMMAND: " + userCommand);
				break;

			}
		}
	}
}
