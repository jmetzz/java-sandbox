package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;


public class Fibonacci {

    public static long evaluate(long n){
        if ((n == 0) || (n == 1))
            return n;

        return evaluate(n-1) + evaluate(n-2);
    }
}
