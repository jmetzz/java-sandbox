package com.github.jmetzz.challenges.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jean Metz.
 */
public class FileHelper {

    private static Logger logger = Logger.getLogger(FileHelper.class.getName());

    public static void save(String fName, String buffer, String charsetName) throws IOException {
        checkNotNull(fName);
        Path file = Paths.get(fName);

        if (Files.exists(file)) {
            logger.info("this already file exists");
            System.out.println(loadFileAsStringList(fName, charsetName));
        } else {
            try (BufferedWriter writer = Files.newBufferedWriter(file, Charset.forName(charsetName))) {
                writer.write(buffer);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "An error has occurred while saving the file : " + fName, e);
            }
        }
    }

    public static List<String> loadFileAsStringList(String fName, String charsetName) throws IOException {
        checkNotNull(fName);

        Path file = Paths.get(fName);
        checkArgument(Files.exists(file));
        logger.info("The file is there :)");

        List<String> contents = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file, Charset.forName(charsetName))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                contents.add(line.trim());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error has occurred while reading the file : " + fName, e);
            throw e;
        }
        return contents;
    }

    public static String loadFileAsString(String fName, String charsetName) throws IOException {
        StringBuilder contents = new StringBuilder();
        loadFileAsStringList(fName, charsetName).forEach(contents::append);
        return contents.toString();
    }

}
