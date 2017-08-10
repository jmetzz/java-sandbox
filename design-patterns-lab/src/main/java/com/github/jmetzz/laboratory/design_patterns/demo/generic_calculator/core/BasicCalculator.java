package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.IEvaluator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class BasicCalculator extends AbstractCalculator {

    // Always use the classname, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(BasicCalculator.class.getName());

    public BasicCalculator(IEvaluator evaluator) {
        super(evaluator);

        Set<Operator> operators = new HashSet<Operator>();
        operators.add(new Sum());
        operators.add(new Sub());
        operators.add(new Multiplication());
        operators.add(new Division());
        operators.add(new Mod());

        getEvaluator().registerOperators(operators);
        LOGGER.info("Basic calculator built");
    }

}
