package org.ecwid;


import org.apache.log4j.Logger;

import java.io.File;

import static org.ecwid.ArgumentsUtil.ARGUMENTS_PATTERN;
import static org.ecwid.ArgumentsUtil.getMaxSpeedInBytes;
import static org.ecwid.ArgumentsUtil.isArgumentsFormatValid;

/**
 * Author: Semernitskaya
 */
public class ApplicationStarter {

    private static final Logger LOGGER = Logger.getLogger(ApplicationStarter.class);

    private static int threadCount;

    private static long maxSpeedInBytes;

    private static File dataFile;

    private static File outputDir;


    public static void main(String[] args) {
        long timeBefore = System.currentTimeMillis();
        try {
            readParameters(args);
            createOutputDirIfNeed();
            DownloadDataReader dataReader = new DownloadDataReader(dataFile, outputDir);
            Downloader downloader = new Downloader(threadCount, maxSpeedInBytes, dataReader.readData());
            downloader.download();
        } catch (Exception e) {
            LOGGER.error("Error while downloading files", e);
        }
        long timeAfter = System.currentTimeMillis();
        LOGGER.info(String.format("Total execution time %s", timeAfter - timeBefore));
    }

    private static void createOutputDirIfNeed() {
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    private static void readParameters(String[] args) {
        if (isArgumentsFormatValid(args)) {
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
}
