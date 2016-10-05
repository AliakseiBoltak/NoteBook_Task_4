package by.epam.notebook.bean;


public class ReadNoteBookFromFileRequest  extends Request  {
	
	private String filePath;

    public ReadNoteBookFromFileRequest() {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
