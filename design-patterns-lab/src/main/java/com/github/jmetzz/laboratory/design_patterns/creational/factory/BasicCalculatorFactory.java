package com.github.jmetzz.laboratory.design_patterns.creational.factory;


import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.AbstractCalculator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.BasicCalculator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.IEvaluator;
import com.google.common.base.MoreObjects;

public class BasicCalculatorFactory implements CalculatorAbstractFactory {


    private final IEvaluator evaluator;

    public BasicCalculatorFactory(IEvaluator evaluator){
        this.evaluator = evaluator;
    }

    @Override
    public AbstractCalculator createCalculator() {
        return new BasicCalculator(evaluator);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("evaluator", evaluator)
                .toString();
    }
}
