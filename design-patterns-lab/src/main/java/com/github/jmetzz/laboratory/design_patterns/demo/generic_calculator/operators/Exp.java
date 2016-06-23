package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

import com.google.common.base.Preconditions;

import java.util.logging.Logger;

public class Exp extends Operator {

	private final static Logger LOGGER = Logger.getLogger(Exp.class
			.getName());
	public static final String OPERATOR_EXP = "Exponentiation";
	public static final String DEFAULT_EXP_SYMBOL = "^";

	public Exp() {
		super(OPERATOR_EXP, DEFAULT_EXP_SYMBOL, Associativity.LEFT, 10);
	}

	public Exp(String symbol){
		super(DEFAULT_EXP_SYMBOL, symbol, Associativity.LEFT, 0);
	}

	@Override
	public Double eval(Double... operands) throws ArithmeticException {
		Preconditions.checkArgument(operands.length == 2, "Exponent operation can only be used with two operands");
		return Math.pow(operands[0], operands[1]);
	}

}
