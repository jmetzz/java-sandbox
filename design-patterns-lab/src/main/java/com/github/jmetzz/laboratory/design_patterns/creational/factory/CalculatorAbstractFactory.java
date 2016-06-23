package com.github.jmetzz.laboratory.design_patterns.creational.factory;


import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.AbstractCalculator;

public interface CalculatorAbstractFactory {

    AbstractCalculator createCalculator();

}
