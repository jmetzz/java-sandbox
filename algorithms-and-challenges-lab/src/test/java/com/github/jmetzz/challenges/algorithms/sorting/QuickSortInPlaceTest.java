package com.github.jmetzz.challenges.algorithms.sorting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameters;


/**
 * Created by Jean Metz.
 */

@RunWith(Parameterized.class)
public class QuickSortInPlaceTest {

    private String input;
    private String expected;

    public QuickSortInPlaceTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0,1,2,3,4,5,6,7,8,9", "0,1,2,3,4,5,6,7,8,9"},
                {"9,8,7,6,5,4,3,2,1,0", "0,1,2,3,4,5,6,7,8,9"},
                {"3,7,8,5,2,1,9,5,4", "1,2,3,4,5,5,7,8,9"}
        });
    }

    @Test
    public void shouldSortArrays() {

        List<Integer> list = Arrays.stream(input.split(","))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Integer[] array = list.toArray(new Integer[]{});
        QuickSortInPlace.sort(array);
        String actual = Arrays.stream(array)
                .map(i -> i.toString())
                .collect(Collectors.joining(","));
        assertThat(actual).isEqualTo(expected);
    }
}