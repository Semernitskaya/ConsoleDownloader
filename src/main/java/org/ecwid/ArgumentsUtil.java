package org.ecwid;

import org.apache.log4j.Logger;

/**
 * Created by Semernitskaya
 */
public class ArgumentsUtil {

    private static final Logger LOGGER = Logger.getLogger(ArgumentsUtil.class);

    public static final int THREAD_COUNT_INDEX = 1;
    public static final int MAX_SPEED_INDEX = 3;
    public static final int OUTPUT_DIR_INDEX = 5;
    public static final int DATA_FILE_INDEX = 7;
    public static final String ARGUMENTS_PATTERN = "-n <thread count> -l <max speed> -o <output directory path> -f <links data file path>";
    public static final String MEGABYTE_SUFFIX = "m";
    public static final String KILOBYTE_SUFFIX = "k";
    public static final int MULTIPLIER = 1024;

    public static long getMaxSpeedInBytes(String arg) {
        if (arg.toLowerCase().endsWith(MEGABYTE_SUFFIX)) {
            return Long.parseLong(arg.substring(0, arg.length() - 1)) * MULTIPLIER * MULTIPLIER;
        }
        if (arg.toLowerCase().endsWith(KILOBYTE_SUFFIX)) {
            return Long.parseLong(arg.substring(0, arg.length() - 1)) * MULTIPLIER;
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

