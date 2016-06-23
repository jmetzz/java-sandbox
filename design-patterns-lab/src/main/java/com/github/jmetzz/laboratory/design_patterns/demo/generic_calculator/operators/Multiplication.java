package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

import com.google.common.base.Preconditions;

import java.util.logging.Logger;

public class Multiplication extends Operator {

	private final static Logger LOGGER = Logger.getLogger(Multiplication.class
			.getName());

	public Multiplication() {
		super("Multiplication", "*", Associativity.LEFT, 5);
	}

	@Override
	public Double eval(Double... operands) throws ArithmeticException {
		Preconditions.checkArgument(operands.length == 2, "Multiplication operation can only be used with two operands");
		return operands[0] * operands[1];
	}

}
