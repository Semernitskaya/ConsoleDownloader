package org.ecwid;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;

/**
 * Created by Semernitskaya
 *
 * class for downloading file from single URL
 */
public class SingleFileDownloader implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(SingleFileDownloader.class);

    public static final int BUFF_SIZE = 1024;

    private String urlStr;

    private File localFile;

    private RateLimiter rateLimiter;

    private long downloadedBytes;

    public SingleFileDownloader(String urlStr, File localFile, RateLimiter rateLimiter) {
        this.urlStr = urlStr;
        this.localFile = localFile;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {
        LOGGER.info("Start downloading file from URL " + urlStr);
        BufferedInputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(urlStr);
            in = new BufferedInputStream(url.openStream());
            out = new FileOutputStream(localFile);
            final byte data[] = new byte[BUFF_SIZE];
            int count;
            while ((count = in.read(data, 0, BUFF_SIZE)) != -1) {
                rateLimiter.acquire(BUFF_SIZE);
                LOGGER.debug("Reading chunk of data from URL " + urlStr);
                downloadedBytes += count;
                out.write(data, 0, count);
            }
            LOGGER.info("End downloading file from URL " + urlStr);
        } catch (Exception e) {
            LOGGER.warn("Error downloading file from URL " + urlStr, e);
        } finally {
            closeResources(in, out);
        }
    }

    private void closeResources(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    LOGGER.warn("Error closing resources", e);
                }
            }
        }
    }

    public long getDownloadedBytes() {
        return downloadedBytes;
    }
}
