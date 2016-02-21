package com.github.jmetzz.challenges.primeNumbers;

/**
 * Created by Jean Metz.
 */
public class PrimeFactorBWImpl implements PrimeFactor {

    @Override
    public boolean isPrimeNumber(int number) {
        for (int i = 2; i <= (number >> 1); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
