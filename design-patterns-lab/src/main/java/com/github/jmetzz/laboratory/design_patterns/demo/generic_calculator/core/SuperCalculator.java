package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.IEvaluator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class SuperCalculator extends AbstractCalculator {

    // Always use the classname, this way you can refactor
	private final static Logger LOGGER = Logger.getLogger(SuperCalculator.class.getName());


    public SuperCalculator(IEvaluator evaluator) {
        super(evaluator);

        Set<Operator> operators = new HashSet<Operator>();
        operators.add(new Sum());
        operators.add(new Sub());
        operators.add(new Multiplication());
        operators.add(new Division());
        operators.add(new Mod());
        operators.add(new Exp());
        operators.add(new Root());

        getEvaluator().registerOperators(operators);

        LOGGER.info("super calculator built");
    }

}
