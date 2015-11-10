package com.github.jmetzz.guava;

import java.util.List;
import java.util.function.Function;

/**
 * Created by exi853 on 10/11/2015.
 */
public class CrazySplitter {

    String source;

    Function<Character, Integer> firstPosition = new Function<Character, Integer>() {
        @Override
        public Integer apply(Character c) {
            return source.indexOf(c);
        }
    };


    public List<String> extract(String input, Iterable<Character> delimiters, int lenghConstraing) {
        throw new UnsupportedOperationException("Not implemented yet");

    }


}
