package by.epam.notebook.utils;

public class StringUtils {

	public static boolean isValid(String note) {
		if (note.equals("") || note == null || note.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}
}

