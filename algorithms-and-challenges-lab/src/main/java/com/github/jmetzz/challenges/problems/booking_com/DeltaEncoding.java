package com.github.jmetzz.challenges.problems.booking_com;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jean Metz.
 */
public class DeltaEncoding {
    public static void main(String[] args) {

        String newStr = "25626 25757 24367 24267 16 100 2 7277";
        System.out.println(delta(newStr.split(" ")));
    }

    public static String delta(String[] input) {
        List<Integer> values = Arrays.stream(input)
                .map(str -> Integer.valueOf(str))
                .collect(Collectors.toList());

        StringBuffer sb = new StringBuffer();


        sb.append(values.get(0)).append(" ");
        for (int index = 1; index < values.size(); index++) {
            int diff = values.get(index) - values.get(index - 1);

            if (Math.abs(diff) > 127) {
                sb.append("-128 ");
            }
            sb.append(diff).append(" ");
        }
        return sb.toString();

    }
}
