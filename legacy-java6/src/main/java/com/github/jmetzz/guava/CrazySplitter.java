package com.github.jmetzz.guava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exi853 on 10/11/2015.
 */
public class CrazySplitter {

    private boolean trunc;

    private List<Character> delimieters;

    private int maxLength;

    public CrazySplitter(boolean trunc, List<Character> delimieters, int maxLength) {
        this.trunc = trunc;
        this.delimieters = delimieters;
        this.maxLength = maxLength;
    }

    public List<String> split(String source) {

        List<Integer> splitPoints = new ArrayList<Integer>();
        int idx = 0;
        for (Character c : source.toCharArray()) {
            if (delimieters.contains(c))
                splitPoints.add(idx);
            idx++;
        }
        List<String> result = new ArrayList<String>();
        int refIdx = 0;
        for (int i = 1; i < splitPoints.size(); i++) {
            if ((splitPoints.get(i) - refIdx) < maxLength) {
                continue;
            }
            int finalIdx = splitPoints.get(i - 1) + 1;
            result.add(source.substring(refIdx, finalIdx));
            refIdx = finalIdx;
        }
        return result;
    }

}
