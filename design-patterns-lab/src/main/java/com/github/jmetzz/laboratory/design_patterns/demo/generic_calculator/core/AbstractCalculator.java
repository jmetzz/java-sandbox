package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core;


import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.IEvaluator;

import java.util.logging.Logger;

public abstract class AbstractCalculator {
    private final static Logger LOGGER = Logger.getLogger(AbstractCalculator.class.getName());


    private IEvaluator evaluator;
    private double memory;
    private double buffer;

    public AbstractCalculator(IEvaluator evaluator) {
        LOGGER.info("Building calculator");

        buffer = 0.0;
        memory = 0.0;
        this.evaluator = evaluator;
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
