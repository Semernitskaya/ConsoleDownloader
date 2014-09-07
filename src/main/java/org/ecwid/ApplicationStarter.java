package org.ecwid;


import org.apache.log4j.Logger;

import java.io.File;

/**
 * Author: Semernitskaya
 */
public class ApplicationStarter {

    private static final Logger LOGGER = Logger.getLogger(ApplicationStarter.class);

    private static int threadCount;

    private static long maxSpeedInBytes;

    private static File dataFile;

    private static File outputDir;

    private static final String ARGUMENTS_PATTERN = "-n <thread count> -l <max speed> -o <output directory path> -f <links data file path>";

    public static void main(String[] args) {
        long timeBefore = System.currentTimeMillis();
        try {
            readParameters(args);
            DownloadDataReader dataReader = new DownloadDataReader(dataFile, outputDir);
            Downloader downloader = new Downloader(threadCount, maxSpeedInBytes, dataReader.readData());
            downloader.download();
        } catch (Exception e) {
            LOGGER.error("Error while downloading files", e);
        }
        long timeAfter = System.currentTimeMillis();
        LOGGER.info(String.format("Total execution time %s", timeAfter - timeBefore));
    }

    private static void readParameters(String[] args) {
        if (args.length != 8) {
            LOGGER.error("Invalid arguments count");
            throw new IllegalArgumentException();
        }
        if (!args[0].equals("-n") || !args[2].equals("-l") || !args[4].equals("-o") || !args[6].equals("-f")) {
            LOGGER.error("Invalid arguments format. Arguments must mach pattern " + ARGUMENTS_PATTERN);
            throw new IllegalArgumentException();
        }
        try {
            threadCount = Integer.parseInt(args[1]);
            maxSpeedInBytes = getMaxSpeedInBytes(args[3]);
            outputDir = new File(args[5]);
            dataFile = new File(args[7]);
        } catch (Exception e) {
            LOGGER.error("Invalid arguments format. Arguments must mach pattern " + ARGUMENTS_PATTERN);
            throw new IllegalArgumentException(e);
        }
    }

    private static long getMaxSpeedInBytes(String arg) {
        if (arg.toLowerCase().endsWith("m")) {
            return Long.parseLong(arg.substring(0, arg.length() - 1)) * 1024 * 1024;
        }
        if (arg.toLowerCase().endsWith("k")) {
            return Long.parseLong(arg.substring(0, arg.length() - 1)) * 1024;
        }
        return Long.parseLong(arg);
    }
}
