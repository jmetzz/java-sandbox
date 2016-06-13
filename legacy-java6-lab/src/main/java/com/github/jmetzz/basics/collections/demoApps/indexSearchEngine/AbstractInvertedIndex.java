package com.github.jmetzz.basics.collections.demoApps.indexSearchEngine;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class AbstractInvertedIndex implements Index {

    private String path;

    public AbstractInvertedIndex(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void build() throws IOException {
        File dir = new File(this.path);
        if (!dir.isDirectory())
            throw new IllegalArgumentException("Missing directory: " + this.getClass().getName());
        loadData(dir);
    }

    protected Set<String> loadWords(String fileName) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Set<String> words = Sets.newHashSet();
        String line;
        while ((line = br.readLine()) != null) {
            for (String s : line.split(" "))
                words.addAll(Arrays.asList(clean(s).toLowerCase()));

        }
        return words;
    }

    private String clean(String line) {
        return (line.replaceAll("\\W", "_")).replaceAll("[0-9]", "");
    }

    abstract protected void loadData(File path) throws IOException;

}
