package com.github.jmetzz.challenges.algorithms.strings;

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


import java.util.*;
import java.util.stream.Collectors;

public class StringConstruction_1 {

       private static int getGenerationMinimalCost(String tape) {
        int cost = 0;
        String generatedSoFar = "";
        Set<String> ps = new HashSet<>();
        while (tape.length() > 0) {
            ps.addAll(powerSet(generatedSoFar, ps));
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


    public static Set<String> powerSet(String input, Set<String> ps) {

        Set<String> result = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        result.add(input);
        queue.add(input);

        while (!queue.isEmpty()) {
            String current = queue.pollFirst();
            if (current.length() > 1) {
                List<String> substrings = getLowerOrderString(current);
                Set<String> collect = substrings.stream()
                        .filter(s -> !ps.contains(s))
                        .filter(s -> !queue.contains(s))
                        .collect(Collectors.toSet());
                queue.addAll(collect);
                result.addAll(collect);
            }
        }
        return result;
    }

    public static List<String> getLowerOrderString(String input) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            result.add(input.substring(0, i) + input.substring(i + 1, input.length()));
        }
        return result;
    }

}
