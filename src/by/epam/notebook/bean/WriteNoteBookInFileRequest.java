package by.epam.notebook.bean;


public class WriteNoteBookInFileRequest  extends Request {
	
	private String filePath;

    public WriteNoteBookInFileRequest() {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
