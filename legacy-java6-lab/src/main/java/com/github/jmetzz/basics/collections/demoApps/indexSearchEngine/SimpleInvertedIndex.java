package com.github.jmetzz.basics.collections.demoApps.indexSearchEngine;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SimpleInvertedIndex extends AbstractInvertedIndex implements Index {

    private Map<String, Set<String>> mapFileToWords;
    private Map<String, Set<String>> inverse; // inverted index

    public SimpleInvertedIndex(String path) {
        super(path);
    }


    @Override
    protected void loadData(File dir) throws IOException {
        mapFileToWords = new HashMap<String, Set<String>>();
        inverse = new HashMap<String, Set<String>>();
        File[] files = dir.listFiles();
        for (File f : files) {
            this.mapFileToWords.put(f.getName(), loadWords(f.getAbsolutePath()));
        }
        Iterator<String> docIt = mapFileToWords.keySet().iterator();
        while (docIt.hasNext()) {
            String key = docIt.next();
            register(key, mapFileToWords.get(key));
        }
    }

    private void register(String doc, Set<String> words) {
        for (String word : words) {
            if (!isIndexed(word)) {
                inverse.put(word, new TreeSet<String>());
            }
            inverse.get(word).add(doc);
        }
    }

    @Override
    public boolean isIndexed(String word) {
        return (inverse.get(word) != null);
    }


    @Override
    public Set<String> getWords(String file) {
        return mapFileToWords.get(file);
    }

    @Override
    public Set<String> executeQuery(String query) {
        return inverse.get(query.toLowerCase());
    }

    @Override
    public String toString() {
        return "Database [path="
                + getPath()
                + "Files = " + mapFileToWords.size()
                + "Words = " + inverse.size()
                + "]";
    }

}
