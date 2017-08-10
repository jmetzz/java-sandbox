package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;


import com.google.common.base.Preconditions;

import java.util.Arrays;

public class Sum extends Operator {

	public static final String OPERATOR_SUM = "Sum";
	public static final String DEFAULT_SUM_SYMBOL = "+";

	public Sum() {
		super(OPERATOR_SUM, DEFAULT_SUM_SYMBOL, Associativity.LEFT, 0);
	}

	public Sum(String symbol){
		super(OPERATOR_SUM, symbol, Associativity.LEFT, 0);
	}


	@Override
	public Double eval(Double... operands) throws ArithmeticException {
		Preconditions.checkArgument(operands.length > 0, "Sum operation needs at least one operand");

		return Arrays.asList(operands).stream().mapToDouble(Double::doubleValue).sum();

/*
		Double result = 0.0;
		for(Double e: operands)
			result += e;

		return result;
*/

	}

}
