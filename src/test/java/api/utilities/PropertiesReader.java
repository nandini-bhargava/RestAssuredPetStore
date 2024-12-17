package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	    private static Properties properties;

	    static {
	        try {
	        	
	            // Path to the properties file
	            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
	            properties = new Properties();
	            properties.load(fileInputStream);
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to load config.properties file", e);
	        }
	    }

	    public static String getProperty(String key) {
	        return properties.getProperty(key);
	    }
	}
