package me.shubhamjain.refactoring.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileUtilityTest {

	final private static String FILE_PATH = "src/main/resources/small_pull_requests_data.csv";
	List<String[]> expectedList;

	@Before
	public void setUp() throws Exception {
		expectedList = new ArrayList<>();
		expectedList.add(new String[] { "gocd-agent-scripts", "2016-07-05,0.001389", "elian-stuart" });
		expectedList.add(new String[] { "gocd-agent-scripts", "2016-06-16,0.001111", "elian-stuart" });
	}

	@Test
	public void shouldCreateListOfLinesFromFile() {
		try {
			List<String[]> actualList = FileUtility.getFilesContentsIntoList(FILE_PATH);
			assertEquals(expectedList, actualList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
