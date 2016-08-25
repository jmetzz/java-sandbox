package com.github.jmetzz.challenges.algorithms.strings.matchString;


import java.util.Arrays;
import java.util.List;

public class FiniteAutomatonMatcher {

    private int[] states;
    private String pattern;

    public FiniteAutomatonMatcher(String pattern) {
        this.states = new int[pattern.length()];
        this.pattern = pattern;
    }


    public boolean match(String text) {
        /*int q = 0;
        for (int i = 0; i < text.length(); i++) {
            q = transition(q, text.charAt(i));
            if (q == m)
                return true;
        }*/
        return false;
    }

    private int transition(int q, char c) {
        return 0;
    }

    private void build() {
      /*  int m = pattern.length();
        List<Character> alphabet = Arrays.asList(pattern.toCharArray(), new char[]);
        for(int q = 0; q < m; q++){
            for()
        }
*/
    }


    public int suffix() {
        // Alphabet* -> Integer
        // suffix(String) -> length of the longest prefix of input pattern that is also suffix of text

        return 0;
    }


}
