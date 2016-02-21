package com.github.jmetzz.challenges.algorithms.probability;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.jmetzz.challenges.algorithms.probability.Combination.combination;
import static com.github.jmetzz.challenges.algorithms.probability.Combination.powerset;

/**
 * Created by Jean Metz.
 */
//@RunWith(Parameterized.class)
public class CombinationTest {

//    private Integer[] input;

    private static Integer[] input1 = new Integer[]{2, 3, 6};
    private static Integer[] input2 = new Integer[]{3, 2, 1, 7, 9, 45, 62, 32, 7, 12, 843, 75, 82, 83, 33, 333, 444, 555};


    private long start;
/*

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                new Object[]{1, 2}
//                ,
//                {1},
//                {0, 0},
//                {2, 3, 6},
//                {2, 3, 6, 2, 3, 6},
//                {3, 2, 1, 7, 9, 45, 62},
//                {3, 2, 1, 7, 9, 45, 62, 32, 7, 12, 843, 75, 82, 83, 33, 333, 444, 555}
        });
    }

    public CombinationTest(Integer... input) {
        this.input = input;
    }
*/


    @Before
    public void start() {
        start = System.currentTimeMillis();
    }

    @After
    public void end() {
        System.out.println(System.currentTimeMillis() - start);
    }


    @Test
    public void testCombination() throws Exception {
//        for (int i = 1; i <= input1.length; i++) {
//            combination(input1, i);
//        }

        for (int i = 1; i <= input2.length; i++) {
            combination(input2, i);
        }
    }

    @Test
    public void testPowerset() throws Exception {
//        getUniqueSets(powerset(input1));
        getUniqueSets(powerset(input2));
    }

    @Test
    public void contrastMethods() {
//        testEquality(input1);
        testEquality(input2);
    }

    public void testEquality(Integer[] input) {
        Set<Set<Integer>> comb = new HashSet<>();
        List<List<Integer>> ps = new ArrayList<>();

        for (int i = 1; i <= input.length; i++) {
            comb.addAll(combination(input, i));
        }

        ps.addAll(powerset(input));
        Set<Set<Integer>> unique = getUniqueSets(ps);

        System.out.println(comb);
        System.out.println(" ---- VS ---- ");
        System.out.println(unique);
    }

    private Set<Set<Integer>> getUniqueSets(List<List<Integer>> ps) {
        Set<Set<Integer>> unique = new HashSet<>();

        for (List<Integer> s : ps)
            unique.add(new HashSet<>(s));
        return unique;
    }
}