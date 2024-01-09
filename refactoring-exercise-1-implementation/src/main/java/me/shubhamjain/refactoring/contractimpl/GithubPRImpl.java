package me.shubhamjain.refactoring.contractimpl;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.shubhamjain.refactoring.contract.IPullRequests;
import me.shubhamjain.refactoring.utils.FileUtility;

public class GithubPRImpl implements IPullRequests {

	// private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	// File Name
	private final String fileName;

	// Top Contributors count
	private final int topContributionsSize;

	public GithubPRImpl(String fileName, int topContributionsSize) {
		this.fileName = fileName;
		this.topContributionsSize = topContributionsSize;
	}

	@Override
	public List<String> getTopContributors() {
		Map<String, Integer> userFrequency = new HashMap<>();
		try {
			List<String[]> userStats = FileUtility.getFilesContentsIntoList(this.fileName);
			for (String[] users : userStats) {
				if (userFrequency.containsKey(users[3]))
					userFrequency.put(users[3], userFrequency.get(users[3]) + 1);
				userFrequency.put(users[3], 1);
			}
		} catch (IOException e) {
			// logger.info("Error occured in reading files");
		}
		return userFrequency.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
				.map(Map.Entry::getKey).collect(Collectors.toList()).subList(0, this.topContributionsSize);
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the topContributionsSize
	 */
	public int getTopContributionsSize() {
		return topContributionsSize;
	}

}
