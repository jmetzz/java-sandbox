package com.github.jmetzz.challenges.primeNumbers;

/**
 * Created by Jean Metz.
 */
public class PrimeFactorApacheImpl implements PrimeFactor {

    @Override
    public boolean isPrimeNumber(int number) {
        return org.apache.commons.math3.primes.Primes.isPrime(number);
    }
}
