package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

import com.google.common.base.Preconditions;

import java.util.logging.Logger;

public class Root extends Operator {

	private final static Logger LOGGER = Logger.getLogger(Root.class
			.getName());
	public static final String OPERATOR_ROOT = "Logarithm";
	public static final String DEFAULT_ROOT_SYMBOL = "R";

	public Root() {
		super(OPERATOR_ROOT, DEFAULT_ROOT_SYMBOL, Associativity.LEFT, 10);
	}

	public Root(String symbol){
		super(DEFAULT_ROOT_SYMBOL, symbol, Associativity.LEFT, 0);
	}

	@Override
	public Double eval(Double... operands) throws ArithmeticException {
		Preconditions.checkArgument(operands.length == 2, "Exponent operation can only be used with two operands");
		return Math.pow(Math.E, Math.log(operands[0])/operands[1]);
	}

}
