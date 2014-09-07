package org.ecwid;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Semernitskaya
 */
public class Downloader {

    private int threadsCount;

    private int maxSpeed;

    private DownloadData downloadData;

    public Downloader(int threadsCount, int maxSpeed, DownloadData downloadData) {
        this.threadsCount = threadsCount;
        this.maxSpeed = maxSpeed;
        this.downloadData = downloadData;
    }

    public void download() throws IOException {
        List<SingleFileDownloader> singleFileDownloaders = new ArrayList<SingleFileDownloader>();
        for (Map.Entry<String, List<File>> entry : downloadData.getMap().entrySet()) {
            URL url = new URL(entry.getKey());
            singleFileDownloaders.add(new SingleFileDownloader(url, entry.getValue().get(0)));
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(threadsCount);
        for (SingleFileDownloader singleFileDownloader : singleFileDownloaders) {
            threadPool.execute(singleFileDownloader);
        }
        threadPool.shutdown();
        while (!threadPool.isTerminated()) {
        }
        for (List<File> localFiles : downloadData.getMap().values()) {
            for (int i = 1; i < localFiles.size(); i++) {
                FileUtils.copyFile(localFiles.get(0), localFiles.get(i));
            }
        }
    }
}
