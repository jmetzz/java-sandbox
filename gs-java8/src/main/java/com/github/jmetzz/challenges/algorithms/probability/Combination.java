package com.github.jmetzz.challenges.algorithms.probability;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jean Metz.
 */
public class Combination {

    public static <T> Set<Set<T>> combination(T[] elements, int K) {

        // get the length of the array
        // e.g. for {'A','B','C','D'} => N = 4
        int N = elements.length;

        if (K > N) {
            System.out.println("Invalid input, K > N");
            return null;
        }
        // calculate the possible combinations
        // e.g. c(4,2)
        // c(N, K);

        // get the combination by index
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[K];

        // the result combinations
        Set<Set<T>> result = new HashSet<>();

        // position of current index
        //  if (r = 1)              r*
        //  index ==>        0   |   1   |   2
        //  element ==>      A   |   B   |   C
        int r = 0;
        int index = 0;

        while (r >= 0) {
            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if (index <= (N + (r - K))) {
                combination[r] = index;

                // if we are at the last position print and increase the index
                if (r == K - 1) {

                    //do something with the combination e.g. add to list or print
                    // print(combination, elements);
                    result.add(createSet(combination, elements));
                    index++;
                } else {
                    // select index for next position
                    index = combination[r] + 1;
                    r++;
                }
            } else {
                r--;
                if (r > 0)
                    index = combination[r] + 1;
                else
                    index = combination[0] + 1;
            }
        }

        return result;
    }

    private static <T> Set<T> createSet(int[] combination, T[] elements) {
        Set<T> result = new HashSet<>(combination.length);
        for (int z = 0; z < combination.length; z++) {
            result.add(elements[combination[z]]);
        }
        return result;
    }

    public static int c(int n, int r) {
        int nf = fact(n);
        int rf = fact(r);
        int nrf = fact(n - r);
        int npr = nf / nrf;
        int ncr = npr / rf;

        System.out.println("C(" + n + "," + r + ") = " + ncr);

        return ncr;
    }

    public static int fact(int n) {
        if (n == 0)
            return 1;
        else
            return n * fact(n - 1);
    }


    public static <T> List<List<T>> powerset(T[] elements) {
        List<List<T>> ps = new ArrayList<>();
        List<T> emptySet = new ArrayList<>();
        ps.add(emptySet); // add the empty set

        // for every item in the original list
        for (T item : elements) {
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
