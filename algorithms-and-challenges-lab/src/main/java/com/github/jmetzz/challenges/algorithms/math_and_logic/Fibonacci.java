package com.github.jmetzz.challenges.algorithms.math_and_logic;


public class Fibonacci {


    public static void main(String[] args) {
        int n = 10;
        int[] cache = new int[n];

        System.out.println(naiveFib(n));
        System.out.println(memoFib(n, cache));
    }

    public static int naiveFib(int n) {
        System.out.print(">");
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        return naiveFib(n - 1) + naiveFib(n - 2);
    }

    /**
     * This technique, called memorization, is a very common one to optimize exponential time recursive algorithms.
     *
     * @param n
     * @param memo
     * @return
     */
    public static int memoFib(int n, int[] memo) {
        System.out.print(">");
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        else if (memo[n-1] > 0) return memo[n-1];
        memo[n-1] = memoFib(n - 1, memo) + memoFib(n - 2, memo);
        return memo[n-1];
    }

}
