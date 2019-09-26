package com.chnu.util;

import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertiesUtil {

    private static Logger logger = LoggerUtil.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {}

    public static String getMessage(String key) {
        try {
            return getMessageResourceBundle().getString(key);
        } catch (MissingResourceException ex) {
            logger.error("Unable to find property " + key);
            return key;
        }
    }

    private static ResourceBundle getMessageResourceBundle() {
        return ResourceBundle.getBundle("message");
    }

}
