package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.IEvaluator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.*;


public class BasicCalculator {

    // Always use the classname, this way you can refactor
	private final static Logger LOGGER = Logger.getLogger(BasicCalculator.class.getName());

    private IEvaluator evaluator;
    private double memory;
    private double buffer;

    public BasicCalculator(IEvaluator evaluator) {
		LOGGER.info("Building calculator");
        buffer = 0.0;
        memory = 0.0;
        this.evaluator = evaluator;

        Set<Operator> operators = new HashSet<Operator>();
        operators.add(new Sum());
        operators.add(new Sub());
        operators.add(new Multiplication());
        operators.add(new Division());
        operators.add(new Mod());

        try {
            this.evaluator.registerOperators(operators);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IEvaluator getEvaluator() {
        return evaluator;
    }

    public double getBuffer() {
        return buffer;
    }

    public void storeMemory(double value) {
        this.memory = value;
    }

    public void clearMemory() {
        this.memory = 0.0;
    }

    public void addMemory(double d) {
        this.memory += d;
    }

    public void subMemory(double d) {
        this.memory -= d;
    }

    public double recallMemory() {
        buffer = this.memory;
        return this.memory;
    }

    public void reset() {
        buffer = 0.0;
        memory = 0.0;
    }

    public double calculate(String exp) {
        buffer = evaluator.evaluate(exp.trim());
        return buffer;
    }
}
