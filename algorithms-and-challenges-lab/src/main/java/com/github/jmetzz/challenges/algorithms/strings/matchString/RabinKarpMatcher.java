package com.github.jmetzz.challenges.algorithms.strings.matchString;


import java.util.ArrayList;
import java.util.List;

public class RabinKarpMatcher {
    private final int R;
    private final int q;


    public static void main(String[] args) {
        RabinKarpMatcher eng = new RabinKarpMatcher(101, 31);

        String text = "2359023141526739921314153";
        System.out.println(eng.search(text, "31415"));
    }

    public RabinKarpMatcher(int r, int q) {
        this.R = r;
        this.q = q;
    }

    public List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int hashT = hash(text.substring(0, m));
        int hashP = hash(pattern);

        for (int i = 1; i < n - m; i++) {
            if ((hashP == hashT) && (pattern.equals(text.substring(i, i + m)))) {
                result.add(i);
            }
            hashT = hash(text.substring(i + 1, i + m + 1));
        }
        return result;
    }

    public int hash(String str) {
        return str.hashCode();
        /**
         * change this method to use a liner hash calculator, as in RabinKarp proposal
         */
    }

}
