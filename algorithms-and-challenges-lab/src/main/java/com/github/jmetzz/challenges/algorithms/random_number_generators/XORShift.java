package com.github.jmetzz.challenges.algorithms.random_number_generators;


/**
 * Encapsulates a cheap medium quality random number generator.
 * (Marsaglia, 2003)
 */
public class XORShift implements RandomNumberGenerator {


    public int generate(int seed){
        seed ^= (seed << 6);
        seed ^= (seed >>> 21);
        seed ^= (seed << 7);
        return seed;
    }
}

