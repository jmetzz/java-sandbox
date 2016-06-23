package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

import com.google.common.base.Preconditions;

public class Sub extends Operator {

    public static final String OPERATOR_SUBTRACTION = "Subtraction";
    public static final String DEFAULT_SUBTRACTION_SYMBOL = "-";

    public Sub() {
        super(OPERATOR_SUBTRACTION, DEFAULT_SUBTRACTION_SYMBOL, Associativity.LEFT, 0);
    }

    public Sub(String symbol){
        super(OPERATOR_SUBTRACTION, symbol, Associativity.LEFT, 0);
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
