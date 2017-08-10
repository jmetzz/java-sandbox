package com.github.jmetzz.challenges.primeNumbers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Jean Metz.
 */
public class PrimeFactorTest {


    private long start;

    @Before
    public void start() {
        start = System.currentTimeMillis();
    }

    @After
    public void end() {
        System.out.println(System.currentTimeMillis() - start);
    }


    @Test
    public void divCheckerTest() {
        performTest(new PrimeFactorDivImpl());
    }

    @Test
    public void bwCheckerTest() {
        performTest(new PrimeFactorBitWiseImpl());
    }

    @Test
    public void apacheCheckerTest() {
        performTest(new PrimeFactorApacheImpl());
    }

    public void performTest(PrimeFactor checker) {
        assertThat(checker.isPrimeNumber(4)).isFalse();

        int bigOne = 96973;

        assertThat(checker.isPrimeNumber(bigOne)).isTrue();
        assertThat(checker.isPrimeNumber(bigOne + 1)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 2)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 3)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 4)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 5)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 6)).isTrue();
        assertThat(checker.isPrimeNumber(bigOne + 7)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 8)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 9)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 10)).isFalse();
        assertThat(checker.isPrimeNumber(bigOne + 11)).isFalse();

        assertThat(checker.isPrimeNumber(96997)).isTrue();

        for (Integer i : PrimeFactor.morePrimes)
            assertThat(checker.isPrimeNumber(i)).isTrue();
    }

}

