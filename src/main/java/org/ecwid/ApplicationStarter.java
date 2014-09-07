package org.ecwid;


import org.apache.log4j.Logger;

/**
 * Author: Semernitskaya
 */
public class ApplicationStarter {

    private static final Logger LOGGER = Logger.getLogger(ApplicationStarter.class);

    public static void main(String[] args) {
        try {

        } catch (Exception e) {
            LOGGER.error("Error while downloading files", e);
        }
    }
}
