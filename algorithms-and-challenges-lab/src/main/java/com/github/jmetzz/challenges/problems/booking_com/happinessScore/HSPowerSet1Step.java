package com.github.jmetzz.challenges.problems.booking_com.happinessScore;

import com.github.jmetzz.challenges.primeNumbers.PrimeFactor;
import com.github.jmetzz.challenges.primeNumbers.PrimeFactorDivImpl;
import com.github.jmetzz.challenges.util.CollectionsHelper;

import java.io.IOException;
import java.util.*;

/**
 * Created by Jean Metz.
 */
public class HSPowerSet1Step extends AbstractHappinessScore {

    public HSPowerSet1Step(PrimeFactor primeChecker) {
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
//        initialSet.addAll(Arrays.asList(new Integer[]{3, 2, 6, 96973}));

        HSPowerSet1Step hs = new HSPowerSet1Step(new PrimeFactorDivImpl());
        Set<Integer> primes = hs.primeSatisfactionScores(initialSet.size(), initialSet);

        System.out.println(primes);
        System.out.println(primes.size());
    }

    @Override
    public Set<Integer> satisfactionScores(int n, List<Integer> localtionScore) {
        throw new UnsupportedOperationException("Method not available");
    }

    @Override
    public Set<Integer> primeSatisfactionScores(int n, List<Integer> localtionScore) {

        List<Collection<Integer>> ps = new ArrayList<>();
        ps.add(new ArrayList<>()); // add the empty set

        Set<Integer> primes = new TreeSet<>();
        Integer satisfaction;

        // for every item in the original list
        for (Integer item : localtionScore) {
            List<Collection<Integer>> newPs = new ArrayList<>();
            for (Collection<Integer> subset : ps) {
                // copy all of the current powerset's subsets
                newPs.add(subset);
                // plus the subsets appended with the current item
                Collection<Integer> newSubset = new ArrayList<>(subset);
                newSubset.add(item);
                newPs.add(newSubset);

                satisfaction = CollectionsHelper.sum(newSubset);
                if (getPrimeChecker().isPrimeNumber(satisfaction))
                    primes.add(satisfaction);
            }
            // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
            ps = newPs;
        }
        return primes;
    }


}
