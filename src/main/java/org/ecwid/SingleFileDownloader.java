package org.ecwid;

import java.io.File;
import java.net.URL;

/**
 * Created by Semernitskaya
 */
public class SingleFileDownloader implements Runnable {

    private URL url;

    private File localFile;

    private long downloadedBytes;

    public SingleFileDownloader(URL url, File localFile) {
        this.url = url;
        this.localFile = localFile;
    }

    @Override
    public void run() {

    }
}
