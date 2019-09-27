package com.chnu.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.chnu.util.PropertiesUtil.getProperty;

public class LoggerUtil {

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = LogManager.getLogger(clazz);

        if(Boolean.parseBoolean(getProperty(PropertiesUtil.LOGGER_PROPERTIES, "custom.logging.setup.display"))) {
            logger.info("Setting up logger for class " + clazz.getName());
        }
        return logger;
    }

}
