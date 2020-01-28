package com.aktepe.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // This class will be responsible for loading properties file and will provide access to values based on key names
    // We use Properties class to load custom .properties files
    private static Properties configFile;

    static {
        try {
            // Provides access to file
            FileInputStream fileInputStream = new FileInputStream("config.properties");

            // Initialize properties object
            configFile = new Properties();

            // Load configuration.properties file
            configFile.load(fileInputStream);

            // Close input stream
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return configFile.getProperty(key);
    }
}