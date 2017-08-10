package com.github.jmetzz.challenges.problems.booking_com.happinessScore;

import com.github.jmetzz.challenges.primeNumbers.PrimeFactor;
import com.github.jmetzz.challenges.primeNumbers.PrimeFactorDivImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


/**
 * Created by Jean Metz.
 */
public class HappinessScoreTest {

    public static final PrimeFactor PRIME_CHECKER = new PrimeFactorDivImpl();
    private static List<Integer> initialSet;
    //    public static final PrimeFactor PRIME_CHECKER = new PrimeFactorBWImpl();
//    public static final PrimeFactor PRIME_CHECKER = new PrimeFactorApacheImpl();
    private long start;

    @BeforeClass
    public static void setup() {
        initialSet = new ArrayList<>();
        initialSet.addAll(Arrays.asList(3, 3, 2, 5, 7, 8, 1, 2, 3, 4, 6, 23, 45, 765, 7, 3, 4123, 654, 56));
//        initialSet.addAll(Arrays.asList(new Integer[]{3, 2, 6}));
    }


    @Before
    public void start() {
        start = System.currentTimeMillis();
    }

    @After
    public void end() {
        long etime = System.currentTimeMillis() - start;
        System.out.println(" Times elaspsed :" + etime);
    }


    public Set<Integer> perform(HappinessScore hs, List<Integer> initialSet) {
        return hs.primeSatisfactionScores(initialSet.size(), initialSet);
    }

    public Set<Integer> performAnyScore(HappinessScore hs, List<Integer> initialSet) {
        return hs.satisfactionScores(initialSet.size(), initialSet);
    }

    @Test
    public void HSPowerSet1StepTest() {
        HSPowerSet1Step hs = new HSPowerSet1Step(PRIME_CHECKER);
        perform(hs, initialSet);
    }


    @Test
    public void HSPowerSet2StepsTest() {
        HSPowerSet2Steps hs = new HSPowerSet2Steps(PRIME_CHECKER);
        perform(hs, initialSet);
    }

    @Test
    public void HSCombinationTest() {
        HSCombination hs = new HSCombination(PRIME_CHECKER);
        perform(hs, initialSet);
    }

    @Test
    public void HSCombinationAnyScoreTest() {
        HSCombination hs = new HSCombination(PRIME_CHECKER);
        performAnyScore(hs, initialSet);
    }

}