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
        ArrayList<File> firstUrlFiles = new ArrayList<>();
        firstUrlFiles.add(new File(OUTPUT_DIR, "archive.zip"));
        firstUrlFiles.add(new File(OUTPUT_DIR, "archive_1.zip"));
        ArrayList<File> secondUrlFiles = new ArrayList<>();
        secondUrlFiles.add(new File(OUTPUT_DIR, "image.bmp"));
        DownloadData downloadData = new DownloadData();
        downloadData.getMap().put("http://example.com/archive.zip", firstUrlFiles);
        downloadData.getMap().put("http://example.com/image.bmp", secondUrlFiles);
        runTestCase("url_list_1.txt", downloadData);
    }

    void runTestCase(String fileName, DownloadData expectedDownloadData) {
        File dataFile = new File(getClass().getClassLoader().getResource(fileName).getPath());
        DownloadDataReader downloadDataReader = new DownloadDataReader(dataFile, OUTPUT_DIR);
        Assert.assertEquals(expectedDownloadData, downloadDataReader.readData());
    }
}
