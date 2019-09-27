package com.chnu.util;

import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertiesUtil {

    public static final String MESSAGE_PROPERTIES = "message";
    public static final String LOGGER_PROPERTIES = "log4j2";
    public static final String SYSTEM_PROPERTIES = "system";

    private static Logger logger = LoggerUtil.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {}

    public static String getMessage(String key) {
        return getProperty(MESSAGE_PROPERTIES, key);
    }

    public static String getProperty(String resourceName, String key) {
        try {
            return getResourceBundle(resourceName).getString(key);
        } catch (MissingResourceException ex) {
            logger.error("Unable to find property " + key);
            return null;
        }
    }

    private static ResourceBundle getResourceBundle(String name) {
        return ResourceBundle.getBundle(name);
    }

}
