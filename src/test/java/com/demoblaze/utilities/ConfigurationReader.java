package com.demoblaze.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    //1 Create properties object
    private static Properties properties = new Properties();

    //2 Create static block
    static {
        // Load the file into FileInputStream
        try {
            FileInputStream file = new FileInputStream("configuration.properties");

            // load property object with the file (configuration.properties)
            properties.load(file);

            //close the file
            file.close();

        } catch (IOException e) {
            System.out.println("File not found in Configuration properties");
        }
    }

    //Use the above created logic to create a Re-usable static method
    public static String getProperty (String keyWord) {
        return properties.getProperty(keyWord);
    }
}
