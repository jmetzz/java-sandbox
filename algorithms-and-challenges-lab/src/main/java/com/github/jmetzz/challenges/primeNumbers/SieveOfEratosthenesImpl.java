package com.github.jmetzz.challenges.primeNumbers;

/**
 * Created by Jean Metz.
 */
public class SieveOfEratosthenesImpl {

    /**
     * All we really need to do is to check if n is divisible by a prime number.
     * This is where the Sieve of Eratosthenes comes in.
     * It is a highly efficient way to generate a list of primes.
     * It works by recognizing that all non-prime numbers are divisible by a prime number
     *
     * @param number
     * @return
     */
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
