package com.github.jmetzz.challenges.problems.booking_com.happinessScore;

import com.github.jmetzz.challenges.primeNumbers.PrimeFactor;
import com.github.jmetzz.challenges.primeNumbers.PrimeFactorDivImpl;
import com.github.jmetzz.challenges.util.CollectionsHelper;

import java.io.IOException;
import java.util.*;

/**
 * Created by Jean Metz.
 */
public class HSPowerSet2Steps extends AbstractHappinessScore {


    public HSPowerSet2Steps(PrimeFactor primeChecker) {
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


        HSPowerSet2Steps hs = new HSPowerSet2Steps(new PrimeFactorDivImpl());
        Set<Integer> primes = hs.primeSatisfactionScores(initialSet.size(), initialSet);

        System.out.println(primes);
        System.out.println(primes.size());
    }

    @Override
    public Set<Integer> satisfactionScores(int n, List<Integer> localtionScore) {
        List<List<Integer>> powerSet = powerset(localtionScore);
        powerSet.remove(0);

        Set<Integer> scores = new TreeSet<>();
        int satisfaction;
        for (List<Integer> set : powerSet) {
            satisfaction = CollectionsHelper.sum(set);
            scores.add(satisfaction);
        }
        return scores;
    }

    @Override
    public Set<Integer> primeSatisfactionScores(int n, List<Integer> localtionScore) {

        List<List<Integer>> powerSet = powerset(localtionScore);
        powerSet.remove(0);

        Set<Integer> primes = new TreeSet<>();
        int satisfaction;
        for (List<Integer> set : powerSet) {
            satisfaction = CollectionsHelper.sum(set);
            if (getPrimeChecker().isPrimeNumber(satisfaction))
                primes.add(satisfaction);
        }
        return primes;
    }

    private <T> List<List<T>> powerset(Collection<T> list) {
        List<List<T>> ps = new ArrayList<>();
        List<T> emptySet = new ArrayList<>();
        ps.add(emptySet); // add the empty set

        // for every item in the original list
        for (T item : list) {
            List<List<T>> newPs = new ArrayList<>();
            for (List<T> subset : ps) {
                // copy all of the current powerset's subsets
                newPs.add(subset);
                // plus the subsets appended with the current item
                List<T> newSubset = new ArrayList<>(subset);
                newSubset.add(item);
                newPs.add(newSubset);
            }
            // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
            ps = newPs;
        }
        return ps;
    }


}
