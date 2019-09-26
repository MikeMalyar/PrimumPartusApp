package com.chnu.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = LogManager.getLogger(clazz);

        logger.info("Setting up logger for class " + clazz.getName());
        return logger;
    }

}
