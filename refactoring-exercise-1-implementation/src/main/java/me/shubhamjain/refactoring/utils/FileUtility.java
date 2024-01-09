package me.shubhamjain.refactoring.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtility {

	private FileUtility() {

	}

	public static List<String[]> getFilesContentsIntoList(String fileName) throws IOException {
		List<String[]> pullRequests = new ArrayList<>();
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			pullRequests.addAll(lines.map(line -> line.split(",")).collect(Collectors.toList()));
		}
		return pullRequests;
	}

}
