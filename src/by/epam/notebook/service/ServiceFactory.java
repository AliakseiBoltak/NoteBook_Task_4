package by.epam.notebook.service;

import by.epam.notebook.service.impl.NoteBookServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	private NoteBookService nbService = (NoteBookService) new NoteBookServiceImpl();
	
	
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	
	public NoteBookService getNoteBookService(){
		return nbService;
	}

}
