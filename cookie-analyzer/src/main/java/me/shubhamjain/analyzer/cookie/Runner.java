package me.shubhamjain.analyzer.cookie;

import me.shubhamjain.analyzer.cookie.filedownload.LoadFile;
import me.shubhamjain.analyzer.cookie.filedownload.LoadS3File;
import me.shubhamjain.analyzer.cookie.parser.CookieCSVParser;
import me.shubhamjain.analyzer.cookie.parser.IParser;
import me.shubhamjain.analyzer.cookie.processor.DefaultCookieProcessor;
import me.shubhamjain.analyzer.cookie.processor.Processor;
import me.shubhamjain.analyzer.cookie.model.CookieResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Runner {
    public static void main(String[] args) {
        if (args.length < 2) {
            log.error("Please pass date and filename arguments");
            System.out.println("Please pass date and filename arguments");
            return;
        }
        String date = args[0];
        String filename = args[1];
        log.debug("Date {} and Filename {} passed ", date, filename);
        IParser cookieParser = new CookieCSVParser();
        LoadFile loadFile = new LoadS3File();
        Processor p = new DefaultCookieProcessor(date, filename, cookieParser, loadFile);
        List<CookieResult> cookieResultList = p.applyFilters();
        for (CookieResult c : cookieResultList) {
            System.out.println(c.getCookie());
            log.info("Cookie Result {}", c.getCookie());
        }
    }
}
