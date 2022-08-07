package com.pp.refactoring.contractimpl;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.pp.refactoring.contract.IPullRequests;

public class GithubPRImplTest {

	final private static String FILE_PATH = "src/main/resources/pull_requests_data.csv";
	List<String[]> pullRequests;
	IPullRequests githubPR;

	@Before
	public void setUp() throws Exception {
		pullRequests = new ArrayList<>();
		try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
			pullRequests.addAll(lines.map(line -> line.split(",")).collect(Collectors.toList()));
		}
	}

	@Test
	public void shouldReturnTopTwoContributors() {
		githubPR = new GithubPRImpl(FILE_PATH, 2);
		List<String> expectedContributors = new ArrayList<>();
		expectedContributors.add("lea-mccarthy");
		expectedContributors.add("dashawn-miller");
		List<String> actualContributors = githubPR.getTopContributors();
		assertEquals(expectedContributors, actualContributors);
	}

}
