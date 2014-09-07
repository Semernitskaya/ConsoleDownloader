package org.ecwid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Semernitskaya
 */
public class Downloader {

    private static final Logger LOGGER = Logger.getLogger(Downloader.class);

    private int threadsCount;

    private int maxSpeed;

    private DownloadData downloadData;

    public Downloader(int threadsCount, int maxSpeed, DownloadData downloadData) {
        this.threadsCount = threadsCount;
        this.maxSpeed = maxSpeed;
        this.downloadData = downloadData;
    }

    public void download() {
        LOGGER.info("Start downloading files");
        long timeBefore = System.currentTimeMillis();
        List<SingleFileDownloader> singleFileDownloaders = new ArrayList<SingleFileDownloader>();
        for (Map.Entry<String, List<File>> entry : downloadData.getMap().entrySet()) {
            singleFileDownloaders.add(new SingleFileDownloader(entry.getKey(), entry.getValue().get(0)));
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(threadsCount);
        for (SingleFileDownloader singleFileDownloader : singleFileDownloaders) {
            threadPool.execute(singleFileDownloader);
        }
        waitForExecution(threadPool);
        copyLoadedFiles();
        LOGGER.info("End downloading files");
        LOGGER.info(String.format("Total bytes downloaded %s", aggregateDownloadedBytes(singleFileDownloaders)));
    }

    private long aggregateDownloadedBytes(List<SingleFileDownloader> singleFileDownloaders) {
        long totalDownloadedBytes = 0;
        for (SingleFileDownloader singleFileDownloader : singleFileDownloaders) {
            totalDownloadedBytes += singleFileDownloader.getDownloadedBytes();
        }
        return totalDownloadedBytes;
    }

    private void copyLoadedFiles() {
        for (List<File> localFiles : downloadData.getMap().values()) {
            for (int i = 1; i < localFiles.size(); i++) {
                File srcFile = localFiles.get(0);
                File dstFile = localFiles.get(i);
                try {
                    FileUtils.copyFile(srcFile, dstFile);
                } catch (Exception e) {
                    LOGGER.warn(String.format("Error coping files loaded from same URL, from file %s, to file %s", srcFile, dstFile), e);
                }
            }
        }
    }

    private void waitForExecution(ExecutorService threadPool) {
        threadPool.shutdown();
        while (!threadPool.isTerminated()) {
        }
    }
}
