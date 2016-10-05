package by.epam.notebook.bean;

public class FindNotesByContentRequest extends Request{
	
	
	   private String content;

	    public FindNotesByContentRequest() {

	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }
	

}
