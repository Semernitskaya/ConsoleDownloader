package org.ecwid;

import org.apache.log4j.Logger;

/**
 * Created by Semernitskaya
 */
public class ArgumentsUtil {

    private static final Logger LOGGER = Logger.getLogger(ApplicationStarter.class);

    public static final String ARGUMENTS_PATTERN = "-n <thread count> -l <max speed> -o <output directory path> -f <links data file path>";

    public static long getMaxSpeedInBytes(String arg) {
        if (arg.toLowerCase().endsWith("m")) {
            return Long.parseLong(arg.substring(0, arg.length() - 1)) * 1024 * 1024;
        }
        if (arg.toLowerCase().endsWith("k")) {
            return Long.parseLong(arg.substring(0, arg.length() - 1)) * 1024;
        }
        return Long.parseLong(arg);
    }

    public static boolean isArgumentsFormatValid(String[] args) {
        if (args.length != 8) {
            LOGGER.error("Invalid arguments count");
            return false;
        }
        if (!args[0].equals("-n") || !args[2].equals("-l") || !args[4].equals("-o") || !args[6].equals("-f")) {
            LOGGER.error("Invalid arguments format. Arguments must mach pattern " + ARGUMENTS_PATTERN);
            return false;
        }
        return true;
    }

    private ArgumentsUtil() {
    }
}

