package com.github.jmetzz.core.proxy.dummyService;

/**
 * Created by Jean Metz.
 */
public class CalculatorImpl implements ICalculator {
    public int add(int left, int right) {
        return left + right;
    }
}
