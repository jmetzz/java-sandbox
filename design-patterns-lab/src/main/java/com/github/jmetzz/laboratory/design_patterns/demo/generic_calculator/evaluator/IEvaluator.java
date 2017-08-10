package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Operator;

import java.util.Set;


public interface IEvaluator {
	public double evaluate(String exp);

	public void registerOperators(Set<Operator> opTable);

	public void registerOperator(Operator op) throws Exception;

	public Operator getOperator(String token);

	public boolean isOperator(String symbol);

}
