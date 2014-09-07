package org.ecwid;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Semernitskaya
 */
public class DownloadData {

    private File outputDir;

    private final Map<URL, List<File>> map = new HashMap<URL, List<File>>();

    public DownloadData(File outputDir) {
        this.outputDir = outputDir;
    }

    public void add(String link, String fileName) {

    }

    public Map<URL, List<File>> getMap() {
        return map;
    }
}
