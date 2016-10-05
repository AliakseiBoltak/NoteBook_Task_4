package by.epam.notebook.bean;


public class FindNotesByDateRequest extends Request {
	
	private String date;

    public FindNotesByDateRequest() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
	

}
