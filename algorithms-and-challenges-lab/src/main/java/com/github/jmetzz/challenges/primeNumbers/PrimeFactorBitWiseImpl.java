package com.github.jmetzz.challenges.primeNumbers;

/**
 * Created by Jean Metz.
 */
public class PrimeFactorBitWiseImpl implements PrimeFactor {

    @Override
    public boolean isPrimeNumber(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= (number >> 1); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
