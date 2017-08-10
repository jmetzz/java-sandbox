package com.github.jmetzz.challenges.problems.booking_com;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jean Metz.
 */
public class PoligonMatcher {

    public static void main(String[] args) {


        /*List<String[]> strings = new ArrayList<>();
        strings.add(new String[]{"10", "10", "10", "10"});
        strings.add(new String[]{"10", "1", "10", "10"});
        strings.add(new String[]{"20", "10", "20", "10"});
        strings.add(new String[]{"10", "10", "-10", "10"});
*/


        int s = 0, r = 0, o = 0;
        String line = "10 10 10 10";
        String[] input = line.split(" ");

        List<Integer> values = Arrays.stream(input)
                .map(str -> Integer.valueOf(str))
                .filter(i -> i > 0)
                .collect(Collectors.toList());


        if (values.size() < 4) {
            o++;
        } else {
            long distinctSides = values.stream().distinct().count();
            if (distinctSides == 1)
                s++;
            else if (distinctSides == 2)
                r++;
            else
                o++;
        }
        System.out.println("" + s + " " + r + " " + o);
    }
}
