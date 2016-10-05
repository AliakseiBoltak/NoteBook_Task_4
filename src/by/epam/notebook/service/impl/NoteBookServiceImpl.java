package by.epam.notebook.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import by.epam.notebook.bean.entity.Note;
import by.epam.notebook.bean.entity.NoteBook;
import by.epam.notebook.service.NoteBookService;
import by.epam.notebook.service.exception.ServiceException;
import by.epam.notebook.source.NoteBookProvider;
import by.epam.notebook.utils.FilePathUtils;
import by.epam.notebook.utils.StringUtils;

public class NoteBookServiceImpl implements NoteBookService {

	private static final Date date = new Date();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private static final String dateCurrent = dateFormat.format(date);

	@Override
	public void addNote(String note) throws ServiceException {
		if (!StringUtils.isValid(note)) {
			throw new ServiceException("INCORRECT NOTE");
		}
		Note newNote = new Note(note, dateCurrent);
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		noteBook.add(newNote);
	}

	@Override
	public void clearNoteBook() throws ServiceException {
		NoteBookProvider.getInstance().getNoteBook().clear();
	}

	@Override
	public void readNoteBookFromFile(String filePath) throws ServiceException, IOException {
		if (!FilePathUtils.isValid(filePath)) {
			throw new ServiceException("INCORRECT FILEPATH");
		}
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

		Reader in = null;
		try {
			File file = new File(filePath);
			in = new BufferedReader(new FileReader(file));
			String line = null;
			String note;
			String date;

			while ((line = ((BufferedReader) in).readLine()) != null) {
				String[] temp = line.split("\\|");
				date = temp[0].trim();
				note = temp[1].trim();
				Note n = new Note(note, date);
				noteBook.getNotes().add(n);
			}
		} catch (FileNotFoundException e) {
			throw new ServiceException("INCORRECT FILEPATH");
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	@Override
	public void writeNoteBookInFile(String filePath) throws ServiceException, IOException {
		if (!FilePathUtils.isValid(filePath)) {
			throw new ServiceException("INCORRECT FILEPATH");
		}
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

		Writer out = null;

		try {
			out = new BufferedWriter(new FileWriter(filePath, true));
			for (Note i : noteBook.getNotes()) {
				out.write(i.getDate() + " | " + i.getNote());
				((BufferedWriter) out).newLine();
			}
		} catch (IOException e) {
			throw new ServiceException("INCORRECT FILEPATH");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@Override
	public List<Note> findNotesByContent(String content) throws ServiceException {
		if (!StringUtils.isValid(content)) {
			throw new ServiceException("INCORRECT CONTENT");
		}
		List<Note> result = new ArrayList<>();
		for (Note current : NoteBookProvider.getInstance().getNoteBook().getNotes()) {
			if (current.getNote().contains(content.trim()) && !content.equals("")) {
				result.add(current);
			}
		}
		return result;
	}

	@Override
	public List<Note> findNotesByDate(String date) throws ServiceException {
		if (!StringUtils.isValid(date)) {
			throw new ServiceException("INCORRECT DATE");
		}
		List<Note> result = new ArrayList<>();

		for (Note current : NoteBookProvider.getInstance().getNoteBook().getNotes()) {
			if (current.getDate().equals(date.trim())) {
				result.add(current);
			}
		}
		return result;
	}

	@Override
	public List<Note> showAllNotes() throws ServiceException {
		return NoteBookProvider.getInstance().getNoteBook().getNotes();
	}

	@Override
	public void deserializeNoteBook(String filePath) throws ServiceException {
		if (!FilePathUtils.isValid(filePath)) {
			throw new ServiceException("INCORRECT FILEPATH");
		}
		NoteBook noteBook = null;

		try {
			FileInputStream in = new FileInputStream(filePath);
			ObjectInputStream oin = new ObjectInputStream(in);
			noteBook = (NoteBook) oin.readObject();
		} catch (IOException e) {
			throw new ServiceException();
		} catch (ClassNotFoundException e) {
			throw new ServiceException();
		}
		NoteBookProvider.getInstance().setNoteBook(noteBook);
	}

	@Override
	public void serializeNoteBook(String filePath) throws ServiceException {
		if (!FilePathUtils.isValid(filePath)) {
			throw new ServiceException("INCORRECT FILEPATH");
		}
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(noteBook);
			out.close();
		} catch (IOException e) {
			throw new ServiceException();
		}
	}
}
