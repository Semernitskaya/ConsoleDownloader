package org.ecwid;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Semernitskaya
 */
public class DownloadData {

    private static final Logger LOGGER = Logger.getLogger(DownloadData.class);

    private final Map<String, List<File>> map = new HashMap<>();

    public void add(String url, File file) {
        if (fileWasAdded(file)) {
            LOGGER.warn(String.format("Link for file %s was added previously", file));
            return;
        }
        List<File> files = map.get(url);
        if (files == null) {
            files = new ArrayList<>();
            map.put(url, files);
        }
        files.add(file);
    }

    private boolean fileWasAdded(File file) {
        for (List<File> files : map.values()) {
            if (files.contains(file)) {
                return true;
            }
        }
        return false;
    }

    public Map<String, List<File>> getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownloadData that = (DownloadData) o;
        if (!map.equals(that.map)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return "DownloadData{" +
                "map=" + map +
                '}';
    }
}
