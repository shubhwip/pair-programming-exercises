package me.shubhamjain.wordgame.wordpool;

import me.shubhamjain.wordgame.utils.WordGameUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DefaultPopulateWords implements IPopulateWords {

    @Override
    public Set<String> populateWords(String location) {
        log.info("Populating words from resources location {}", location);
        Set<String> words;
        Optional<String> filePath = WordGameUtils.loadDataFile(location);
        if(filePath.isEmpty()) {
            log.error("File is not found at location {} ", location);
            throw new InvalidWordFileException(new FileNotFoundException("File not found at" + location));
        }
        try (Stream<String> stream = Files.lines(Paths.get(filePath.get()))) {
            words = stream.map(String::toLowerCase).collect(Collectors.toSet());
        } catch (IOException e) {
            log.error("Error occurred in loading words from resources file {}", location, e.getCause());
            throw new InvalidWordFileException(e);
        }
        return words;
    }
}
