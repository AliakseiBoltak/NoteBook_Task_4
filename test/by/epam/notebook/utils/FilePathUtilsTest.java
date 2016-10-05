package by.epam.notebook.utils;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.Arrays;

public class FilePathUtilsTest {

	@Test(dataProvider = "dataProvider")
	public void FilePathUtilsTest(ArrayList<String> str) {
		String invalidPath = (String) str.get(0);
		String validPath = (String) str.get(1);
		Assert.assertFalse(FilePathUtils.isValid(invalidPath));
		Assert.assertTrue(FilePathUtils.isValid(validPath));
	}

	@DataProvider(name = "dataProvider")
	public Object[][] createStrings() {
		return new Object[][] { { new ArrayList<String>(Arrays.asList("", "test/test.txt")) },
				{ new ArrayList<String>(Arrays.asList("src/2.txt", "test/test.txt")) },
				{ new ArrayList<String>(Arrays.asList("src/1.unknown", "test/test.txt")) },
				{ new ArrayList<String>(Arrays.asList("src/", "test/test.txt")) },
				{ new ArrayList<String>(Arrays.asList(" ", "test/test.txt")) }, };
	}
}
