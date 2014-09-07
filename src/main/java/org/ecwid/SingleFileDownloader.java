package org.ecwid;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;

/**
 * Created by Semernitskaya
 */
public class SingleFileDownloader implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(SingleFileDownloader.class);

    private String urlStr;

    private File localFile;

    private long downloadedBytes;

    public SingleFileDownloader(String urlStr, File localFile) {
        this.urlStr = urlStr;
        this.localFile = localFile;
    }

    @Override
    public void run() {
        LOGGER.info("Start downloading file from URL " + urlStr);
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            URL url = new URL(urlStr);
            in = new BufferedInputStream(url.openStream());
            fout = new FileOutputStream(localFile);
            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                downloadedBytes += count;
                fout.write(data, 0, count);
            }
            LOGGER.info("End downloading file from URL " + urlStr);
        } catch (Exception e) {
            LOGGER.warn("Error downloading file from URL " + urlStr, e);
        } finally {
            closeResources(in, fout);
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
