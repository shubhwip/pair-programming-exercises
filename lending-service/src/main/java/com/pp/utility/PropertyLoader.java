package com.pp.utility;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyLoader {
    static Properties properties;

    static {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties = new Properties();
            // load a properties file
            properties.load(input);

        } catch (IOException ex) {
            log.error("Error occurred while loading properties file ,{}", ex.getMessage());
        }
    }

    public static int getIntValue(final String value) {
        return Integer.parseInt(String.valueOf(properties.get(value)));
    }

    public static String getStringValue(final String value) {
        return String.valueOf(properties.get(value));
    }
}
