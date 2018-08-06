package io.github.jhipster.application.web.rest.util;

/**
 * Created by anil on 5/14/18.
 */

import java.util.UUID;

/**
 * This class handles all utility functions related to String
 */
public class GQBStringUtils {
    /**
     * checks whether input string is null (empty) or not.
     *
     * @param inputString
     * @return - true, if given string is null or empty, otherwise false.
     */
    public static boolean isNull(String inputString) {
        if (inputString == null) {
            return true;
        }
        if (inputString.trim().isEmpty() || inputString.trim().equalsIgnoreCase("NULL")) {
            return true;
        }
        return false;
    }

    /**
     * checks whether input string is not null.
     *
     * @param inputString
     * @return - true, if given string is not null or empty, otherwise false.
     */
    public static boolean isNotNull(String inputString) {
        return !isNull(inputString);
    }

    /**
     * Generates random Universal Unique ID
     *
     * @return - unique ID in string format
     */
    public static String getRandomName() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }


}
