package com.github.jmetzz.laboratory.design_patterns.creational.factory;


import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.AbstractCalculator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.BasicCalculator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.SuperCalculator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.IEvaluator;

public class SuperCalculatorFactory implements CalculatorAbstractFactory {

    private final IEvaluator evaluator;

    public SuperCalculatorFactory(IEvaluator evaluator){
        this.evaluator = evaluator;
    }

    @Override
    public AbstractCalculator createCalculator() {
        return new SuperCalculator(evaluator);
    }
}
