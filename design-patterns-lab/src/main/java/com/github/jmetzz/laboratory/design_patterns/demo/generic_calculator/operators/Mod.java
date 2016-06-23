package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

import com.google.common.base.Preconditions;

public class Mod extends Operator {

	public Mod() {
		super("Mod", "%", Associativity.LEFT, 5);
	}

	@Override
	public Double eval(Double... operands) throws ArithmeticException {
		Preconditions.checkArgument(operands.length == 2, "Mod operation can only be used with two operands");
		return operands[0] % operands[1];
	}

}
