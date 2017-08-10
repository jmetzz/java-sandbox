package com.github.jmetzz.challenges.algorithms.strings.matchString;

/**
 * Created by Jean Metz.
 */
public class KMPMatcherplus {
    private String pattern;
    private int[] next;

    // create Knuth-Morris-Pratt NFA from pattern
    public KMPMatcherplus(String pattern) {
        this.pattern = pattern;
        int m = pattern.length();
        next = new int[m];
        int j = -1;
        for (int i = 0; i < m; i++) {
            if (i == 0) next[i] = -1;
            else if (pattern.charAt(i) != pattern.charAt(j)) next[i] = j;
            else next[i] = next[j];
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j];
            }
            j++;
        }

//        for (int i = 0; i < m; i++)
//            System.out.println("next[" + i + "] = " + next[i]);
    }

    // test client
    public static void main(String[] args) {
        String pattern = args[0];
        String text = args[1];
        int m = pattern.length();
        int n = text.length();

        // substring search
        KMPMatcherplus kmp = new KMPMatcherplus(pattern);
        int offset = kmp.search(text);

        // print results
        System.out.println("m = " + m + ", n = " + n);
        System.out.println("text:    " + text);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++)
            System.out.print(" ");
        System.out.println(pattern);
    }

    // return offset of first occurrence of text in pattern (or n if no match)
    // simulate the NFA to find match
    public int search(String text) {
        int m = pattern.length();
        int n = text.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            while (j >= 0 && text.charAt(i) != pattern.charAt(j))
                j = next[j];
            j++;
        }
        if (j == m) return i - m;
        return n;
    }

}
