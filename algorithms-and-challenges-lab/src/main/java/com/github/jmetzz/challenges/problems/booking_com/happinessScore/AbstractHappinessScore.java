package com.github.jmetzz.challenges.problems.booking_com.happinessScore;

import com.github.jmetzz.challenges.primeNumbers.PrimeFactor;

/**
 * Created by Jean Metz.
 */
public abstract class AbstractHappinessScore implements HappinessScore {

    private final PrimeFactor primeChecker;

    public AbstractHappinessScore(PrimeFactor primeChecker) {
        this.primeChecker = primeChecker;
    }

    public PrimeFactor getPrimeChecker() {
        return primeChecker;
    }
}
