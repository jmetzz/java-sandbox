package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

import com.google.common.base.Preconditions;

import java.util.logging.Logger;

public class Division extends Operator {

	private final static Logger LOGGER = Logger.getLogger(Division.class
			.getName());

	public Division() {
		super("Division", "/", Associativity.LEFT, 5);
	}

	@Override
	public Double eval(Double... operands) throws IllegalArgumentException {
		Preconditions.checkArgument(operands.length == 2, "Division operation can only be used with  two operands");
		return operands[0] / operands[1];
	}

}
