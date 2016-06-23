package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

import com.google.common.base.Preconditions;

public class Sub extends Operator {

    public Sub() {
        super("Subtraction", "-", Associativity.LEFT, 0);
    }

    @Override
    public Double eval(Double... operands) throws ArithmeticException {
        Preconditions.checkArgument(operands.length > 1, "Subtraction operation needs at least two operands");

        Double result = operands[0];
		for(int idx = 1;  idx < operands.length; idx++)
			result -= operands[idx];

		return result;

    }
}
