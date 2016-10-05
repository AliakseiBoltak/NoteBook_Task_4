package by.epam.notebook.utils;

import java.io.File;

public class FilePathUtils {

	public static boolean isValid(String filePath) {

		File file = new File(filePath);
		if (!file.exists() || !file.canRead() || !file.canWrite()) {
			return false;
		} else if (file.isDirectory()) {
			return false;
		}
		return true;
	}
}
