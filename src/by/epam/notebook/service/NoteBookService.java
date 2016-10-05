package by.epam.notebook.service;

import java.io.IOException;
import java.util.List;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.service.exception.ServiceException;

public interface NoteBookService {
	
	void addNote(String note) throws ServiceException;
	void clearNoteBook() throws ServiceException;
	void readNoteBookFromFile(String filePath) throws ServiceException, IOException;
	void writeNoteBookInFile(String filePath) throws ServiceException, IOException;
	List<Note> findNotesByContent(String content) throws ServiceException;
	List<Note> findNotesByDate(String date) throws ServiceException;
	List<Note> showAllNotes() throws ServiceException;
    void deserializeNoteBook(String filePath) throws ServiceException;
    void serializeNoteBook(String filePath) throws ServiceException;
}
