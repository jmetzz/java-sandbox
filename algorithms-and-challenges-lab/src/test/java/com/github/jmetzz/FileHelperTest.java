package com.github.jmetzz;

import com.github.jmetzz.challenges.util.FileHelper;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Jean Metz.
 */
public class FileHelperTest {

    public static final String MY_STATIC_FILE = "myStaticFile.txt";
    private static final String STATIC_CONTENT = "Do not change this content or filename";

    @Test
    public void shouldCreateAFile() {
        LocalDateTime timePoint = LocalDateTime.now();
        String content = timePoint.format(DateTimeFormatter.ISO_DATE_TIME);
        try {
            FileHelper.save("myfile" + System.currentTimeMillis() + ".txt", content, "UTF-8");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void shouldSaveTheContentsIntoTheFile() {
        try {
            FileHelper.save(MY_STATIC_FILE, STATIC_CONTENT, "UTF-8");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void shouldReadTheFileContents() {
        try {
            List<String> content = FileHelper.loadFileAsStringList(MY_STATIC_FILE, "UTF-8");
            String actual = content.get(0).toString().trim();
            assertThat(actual).isEqualTo(STATIC_CONTENT);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

}