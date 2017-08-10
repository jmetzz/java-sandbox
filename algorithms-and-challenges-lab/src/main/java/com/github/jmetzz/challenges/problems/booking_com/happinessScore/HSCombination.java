package com.github.jmetzz.challenges.problems.booking_com.happinessScore;

import com.github.jmetzz.challenges.algorithms.probability.Combination;
import com.github.jmetzz.challenges.primeNumbers.PrimeFactor;
import com.github.jmetzz.challenges.primeNumbers.PrimeFactorDivImpl;
import com.github.jmetzz.challenges.util.CollectionsHelper;

import java.io.IOException;
import java.util.*;

/**
 * Created by Jean Metz.
 */
public class HSCombination extends AbstractHappinessScore {

    public HSCombination(PrimeFactor primeChecker) {
        super(primeChecker);
    }

    public static void main(String[] args) throws IOException {

/*
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String cInput = null;
            String ssInput = null;

            cInput = br.readLine();
            ssInput = br.readLine();

            int N = Integer.parseInt(cInput);

        String[] scoresStr = ssInput.split(" ");
        if (N != scoresStr.length)
            System.exit(-1);

        List<Integer> initialSet = new ArrayList<>();
        for (String s : scoresStr) {
            initialSet.add(Integer.parseInt(s));
        }*/

        List<Integer> initialSet = new ArrayList<>();
        initialSet.addAll(Arrays.asList(3, 2, 6));


        HSCombination hs = new HSCombination(new PrimeFactorDivImpl());
        Set<Integer> primes = hs.primeSatisfactionScores(initialSet.size(), initialSet);

        System.out.println(primes);
        System.out.println(primes.size());
    }

    @Override
    public Set<Integer> satisfactionScores(int n, List<Integer> localtionScore) {
        Set<Set<Integer>> c = getSets(localtionScore);

        Set<Integer> scores = new HashSet<>();
        scores.addAll(satisfaction(c));
        return scores;
    }

    @Override
    public Set<Integer> primeSatisfactionScores(int n, List<Integer> localtionScore) {
        Set<Set<Integer>> c = getSets(localtionScore);
        Set<Integer> scores = new HashSet<>();
        scores.addAll(primeSatisfaction(c));
        return scores;
    }

    private Set<Set<Integer>> getSets(List<Integer> localtionScore) {
        Set<Set<Integer>> c = new HashSet<>();
        for (int i = 1; i <= localtionScore.size(); i++) {
            c.addAll(Combination.combination(localtionScore.toArray(new Integer[]{}), i));
        }
        return c;
    }

    private Set<Integer> primeSatisfaction(Set<Set<Integer>> powerSet) {
        Set<Integer> primes = new HashSet<>();
        int satisfaction;
        for (Set<Integer> set : powerSet) {
            satisfaction = CollectionsHelper.sum(set);
            if (getPrimeChecker().isPrimeNumber(satisfaction))
                primes.add(satisfaction);
        }
        return primes;
    }

    private Set<Integer> satisfaction(Set<Set<Integer>> powerSet) {
        Set<Integer> scores = new HashSet<>();
        for (Set<Integer> set : powerSet) {
            scores.add(CollectionsHelper.sum(set));
        }
        return scores;
    }


}
