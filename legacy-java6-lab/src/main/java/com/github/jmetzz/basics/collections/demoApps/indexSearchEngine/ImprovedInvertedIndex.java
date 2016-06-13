package com.github.jmetzz.basics.collections.demoApps.indexSearchEngine;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ImprovedInvertedIndex extends AbstractInvertedIndex implements Index {

    private SetMultimap<String, String> mapFileToWords;
    private SetMultimap<String, String> inverse;

    public ImprovedInvertedIndex(String path) {
        super(path);
        mapFileToWords = HashMultimap.create();
        inverse = HashMultimap.create();
    }

    @Override
    protected void loadData(File dir) throws IOException {
        for (File f : dir.listFiles()) {
            Set<String> words = loadWords(f.getAbsolutePath());
            this.mapFileToWords.putAll(f.getName(), words);
        }
        inverse = Multimaps.invertFrom(mapFileToWords, inverse);
    }


    @Override
    public boolean isIndexed(String word) {
        return inverse != null && inverse.get(word) != null;
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
