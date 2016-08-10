package com.github.jmetzz.challenges.algorithms.strings.permutation;

import com.github.jmetzz.challenges.algorithms.probability.Powerset;

/**
 * Created by Jean Metz.
 */
public class BiggerIsGreater {

    public static void main(String[] args) {
        String input = "hefg";

        Powerset.generate(input)
                .stream()
                .filter(s -> s.length() >= input.length())
                .sorted()
                .forEach(System.out::println);
    }
}
