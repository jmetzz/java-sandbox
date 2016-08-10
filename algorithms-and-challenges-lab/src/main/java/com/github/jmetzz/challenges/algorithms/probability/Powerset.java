package com.github.jmetzz.challenges.algorithms.probability;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Jean Metz.
 */
public class Powerset {


    public static Set<String> generate(String input) {
        return generate(input, new HashSet<>(0));
    }


    public static Set<String> generate(String input, Set<String> ps) {

        Set<String> result = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        result.add(input);
        queue.add(input);

        while (!queue.isEmpty()) {
            String current = queue.pollFirst();
            if (current.length() > 1) {
                List<String> substrings = getLowerOrderString(current);
                Set<String> collect = substrings.stream()
                        .filter(s -> !ps.contains(s))
                        .filter(s -> !queue.contains(s))
                        .collect(Collectors.toSet());
                queue.addAll(collect);
                result.addAll(collect);
            }
        }
        return result;
    }

    private static List<String> getLowerOrderString(String input) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            result.add(input.substring(0, i) + input.substring(i + 1, input.length()));
        }
        return result;
    }

}
