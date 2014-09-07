package org.ecwid;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Semernitskaya
 */
public class DownloadDataReader {

    private static final Logger LOGGER = Logger.getLogger(DownloadData.class);

    private File dataFile;

    private File outputDir;

    public DownloadDataReader(File dataFile, File outputDir) {
        this.dataFile = dataFile;
        this.outputDir = outputDir;
    }

    public DownloadData readData() {
        LOGGER.info("Start reading download data from file " + dataFile);
        DownloadData downloadData = new DownloadData();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.trim().split(" +");
                if (strings.length != 2) {
                    LOGGER.warn("Invalid download data line: " + line);
                }
                downloadData.add(strings[0], new File(outputDir, strings[1]));
            }
            LOGGER.info("End reading download data from file " + dataFile);
            return downloadData;
        } catch (IOException e) {
            LOGGER.error("Error while reading download data from file " + dataFile, e);
            throw new RuntimeException(e);
        }
    }
}
