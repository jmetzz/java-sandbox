package com.github.jmetzz.challenges.primeNumbers;

/**
 * Created by Jean Metz.
 */
public class PrimeFactorSlightlyBetterImpl implements PrimeFactor {

    /**
     * The SQRT(n) is sufficient because, for every number a which divides n evenly,
     * there is a complement b, where a * b = n. If a > SQRT(n), then b < SQRT(n)
     * (since ( /n)2 = n ).
     * We therefore don't need a to check n's primarity,
     * since we would have already checked with b.
     *
     * @param number
     * @return
     */
    @Override
    public boolean isPrimeNumber(int number) {
        if (number < 2) return false;
        int sqrt = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
