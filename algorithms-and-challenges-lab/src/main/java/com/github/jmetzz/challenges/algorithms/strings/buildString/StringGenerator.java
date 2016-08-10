package com.github.jmetzz.challenges.algorithms.strings.buildString;

/**
 * Created by Jean Metz.
 */
public interface StringGenerator {

    int costToGenerate(String tape, int costAppendChar, int costAppendString);
}
