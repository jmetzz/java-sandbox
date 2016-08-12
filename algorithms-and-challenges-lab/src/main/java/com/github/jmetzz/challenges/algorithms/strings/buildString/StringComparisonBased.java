package com.github.jmetzz.challenges.algorithms.strings.buildString;

/*

url: https://www.hackerrank.com/challenges/string-construction

Amanda has a string, s, of m lowercase letters that she wants to copy into a new string, p.
She can perform the following operations any number of times to construct string p:

1. Append a character to the end of string p at a cost of 1 dollar.
2. Choose any substring of p and append it to the end of p at no charge.

Given n strings (i.e., s_0, s_1, ..., s_n-1), find and print the minimum cost of copying each s_i to p_i on a new line.

Input Format
    The first line contains a single integer, , denoting the number of strings.
    Each line i of the n subsequent lines contains a single string, s_i .

Constraints
    1 <= n <= 5
    1 <= m <= 10^5

Output Format
    For each string s_i (where 0 <= i < n), print the minimum cost of constructing string p_i on a new line.

Sample Input

2
abcd
abab


Sample Output

4
2
*/


public class StringComparisonBased implements StringGenerator {

    @Override
    public int costToGenerate(String tape, int costAppendChar, int costAppendString) {
        int cost = 0;
        String buffer = "";
        while (tape.length() > 0) {
            String valueMatch = getLongMatch(tape, buffer);
            if (valueMatch.isEmpty()) {
                cost += costAppendChar;
                valueMatch = tape.substring(0, 1);
            } else
                cost += costAppendString;

            buffer = buffer + valueMatch;
            tape = tape.substring(valueMatch.length());
        }
        return cost;
    }

    private String getLongMatch(String tape, String buffer) {
        boolean matches = true;
        int exclusiveEnd = 1;
        while (matches && exclusiveEnd <= tape.length()) {
            matches = buffer.contains(tape.substring(0, exclusiveEnd));
            if (matches)
                exclusiveEnd++;
        }
        return tape.substring(0, exclusiveEnd - 1);
    }
}
