package org.ecwid;

/**
 * Created by Semernitskaya
 */
public class Downloader {

    private int threadCount;

    private int maxSpeed;

    private DownloadData downloadData;

    public Downloader(int threadCount, int maxSpeed, DownloadData downloadData) {
        this.threadCount = threadCount;
        this.maxSpeed = maxSpeed;
        this.downloadData = downloadData;
    }

    public void download() {

    }
}
