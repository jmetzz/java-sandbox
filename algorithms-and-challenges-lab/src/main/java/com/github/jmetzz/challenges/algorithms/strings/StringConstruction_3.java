package com.github.jmetzz.challenges.algorithms.strings;

/*

url: https://www.hackerrank.com/challenges/build-a-string

Greg wants to build a string, S of length N. Starting with an empty string, he can perform 2 operations:

1. Add a character to the end of S for A dollars.
2. Copy any substring of S, and then add it to the end of S for B dollars.
Calculate minimum amount of money Greg needs to build S.

Input Format

The first line contains number of testcases T.

The 2xT  subsequent lines each describe a test case over 2 lines:
The first contains 3 space-separated integers, N, A , and B, respectively.
The second contains S (the string Greg wishes to build).

Constraints

1 <= T <= 3
1 <= N <= 3x10^4
1 <= A,B <= 10000

S is composed of lowercase letters only.

Output Format

On a single line for each test case, print the minimum cost (as an integer) to build S.

Sample input:

2
9 4 5
aabaacaba
9 8 9
bacbacacb

Sample Output:

26
42

 */


import java.util.Set;

public class StringConstruction_3 {

    public static int getGenerationMinimalCost(String tape, int costA, int costB) {
        int cost = 0;
        String buffer = "";
        while (tape.length() > 0) {
            String valueMatch = getLongMatch(tape, buffer);
            if(valueMatch.isEmpty())
                valueMatch = tape.substring(0, 1);
            buffer = buffer + valueMatch;
            tape = tape.substring(valueMatch.length());
            cost += (valueMatch.length() == 1) ? costA : costB;
//            System.out.println("appended " + valueMatch + ". Total cost is " + cost);
        }
        return cost;
    }

    private static String getLongMatch(String tape, String buffer) {

        boolean matches = true;
        int index = 1;
        while (matches && index < tape.length()) {
            matches = buffer.contains(tape.substring(0, index));
            if(matches)
                index++;
        }
        int boundary = (index == tape.length())? index: index - 1;
        return tape.substring(0, boundary);
    }

}
