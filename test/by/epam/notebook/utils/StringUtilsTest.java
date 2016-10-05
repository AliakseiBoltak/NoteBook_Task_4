package by.epam.notebook.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringUtilsTest {

	@Test(dataProvider = "dataProvider")
	public void StringUtils(ArrayList<String> str) {
		String invalid = (String) str.get(0);
		String valid = (String) str.get(1);
		Assert.assertFalse(StringUtils.isValid(invalid));
		Assert.assertTrue(StringUtils.isValid(valid));
	}

	@DataProvider(name = "dataProvider")
	public Object[][] createStrings() {
		return new Object[][] { { new ArrayList<String>(Arrays.asList("", "a")) },
				{ new ArrayList<String>(Arrays.asList(" ", "1")) },};
	}
}
