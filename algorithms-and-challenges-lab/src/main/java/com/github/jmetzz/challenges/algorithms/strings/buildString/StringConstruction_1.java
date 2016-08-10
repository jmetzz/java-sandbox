package com.github.jmetzz.challenges.algorithms.strings.buildString;

/*

url: https://www.hackerrank.com/challenges/string-construction

Amanda has a string, s, of m  lowercase letters that she wants to copy into a new string, p.
She can perform the following operations any number of times to construct string p:

Append a character to the end of string p at a cost of 1 dollar.
Choose any substring of p and append it to the end of p at no charge.
Given  strings (i.e., s0, s1, ..., sn-1 ), find and print the minimum cost of copying each si to pi on a new line.

Input Format

The first line contains a single integer, , denoting the number of strings.
Each line  of the  subsequent lines contains a single string, si.

Constraints
 1 <= n <= 5
 1 <= m <= 10^5

Output Format

For each string si (where 0 <= i < n), print the minimum cost of constructing string pi on a new line.

Sample Input

2
abcd
abab

Sample Output
4
2


A substring of a string  is another string  that occurs "in"  (Wikipedia). For example, the substrings of the string "abc" are "a", "b" ,"c", "ab", "bc", and "abc".
 */


import com.github.jmetzz.challenges.algorithms.probability.Powerset;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StringConstruction_1 {

       private static int getGenerationMinimalCost(String tape) {
        int cost = 0;
        String generatedSoFar = "";
        Set<String> ps = new HashSet<>();
        while (tape.length() > 0) {
            ps.addAll(Powerset.generate(generatedSoFar, ps));
            Optional<String> longestMatch = getLongestMatch(tape, ps);
            String value = longestMatch.orElse(tape.substring(0, 1));
            cost += (value.length() == 1) ? 1 : 0;
            generatedSoFar = generatedSoFar + value;
            tape = tape.substring(value.length());
        }
        return cost;
    }

    private static Optional<String> getLongestMatch(String tape, Set<String> ps) {
        return ps.stream()
                .filter(s -> !s.isEmpty())
                .filter(pattern -> tape.startsWith(pattern))
                .sorted((s1, s2) -> s2.length() - s1.length())
                .findFirst();
    }

}
