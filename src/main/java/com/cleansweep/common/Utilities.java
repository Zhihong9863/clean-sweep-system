package com.cleansweep.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The utility class will contain general-purpose methods that may be used across multiple classes.
 * For now, we will create a placeholder.
 */
public class Utilities {
    private static final Logger logger = LoggerFactory.getLogger(Utilities.class);

    public static void log(String message) {
        logger.info(message);
    }
}

