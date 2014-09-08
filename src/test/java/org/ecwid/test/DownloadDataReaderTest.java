package org.ecwid.test;

import org.ecwid.DownloadData;
import org.ecwid.DownloadDataReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Semernitskaya
 */
public class DownloadDataReaderTest {

    private static final File OUTPUT_DIR = new File("D:/");

    @Test
    public void case01() {
        DownloadData downloadData = new DownloadData();
        ArrayList<File> files = new ArrayList<>();
        files.add(new File(OUTPUT_DIR, "archive.zip"));
        files.add(new File(OUTPUT_DIR, "archive_1.zip"));
        downloadData.getMap().put("http://example.com/archive.zip", files);
        files = new ArrayList<>();
        files.add(new File(OUTPUT_DIR, "image.bmp"));
        downloadData.getMap().put("http://example.com/image.bmp", files);
        runTestCase("url_list_1.txt", downloadData);
    }

    @Test
    public void case02() {
        DownloadData downloadData = new DownloadData();
        ArrayList<File> files = new ArrayList<>();
        files.add(new File(OUTPUT_DIR, "archive_1.zip"));
        downloadData.getMap().put("http://example.com/archive_1.zip", files);
        files = new ArrayList<>();
        files.add(new File(OUTPUT_DIR, "archive_2.zip"));
        downloadData.getMap().put("http://example.com/archive_2.zip", files);
        files = new ArrayList<>();
        files.add(new File(OUTPUT_DIR, "archive_3_1.zip"));
        files.add(new File(OUTPUT_DIR, "archive_3_2.zip"));
        downloadData.getMap().put("http://example.com/archive_3.zip", files);
        runTestCase("url_list_2.txt", downloadData);
    }

    void runTestCase(String fileName, DownloadData expectedDownloadData) {
        File dataFile = new File(getClass().getClassLoader().getResource(fileName).getPath());
        DownloadDataReader downloadDataReader = new DownloadDataReader(dataFile, OUTPUT_DIR);
        Assert.assertEquals(expectedDownloadData, downloadDataReader.readData());
    }
}
