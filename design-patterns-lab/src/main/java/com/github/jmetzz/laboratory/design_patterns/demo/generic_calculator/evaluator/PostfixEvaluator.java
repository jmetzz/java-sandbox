package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator;


import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Operator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.parser.PostfixParser;
import com.google.common.base.MoreObjects;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class PostfixEvaluator implements IEvaluator {
    protected PostfixParser parser;
    private Set<Operator> operators;

    public PostfixEvaluator() {
        this.parser = new PostfixParser();
    }

    public double evaluate(String exp) throws IllegalArgumentException {

        String[] tokens = parser.parse(exp);

        Deque<String> stack = new ArrayDeque<String>();
        for (String token : tokens) {
            // If the token is a value push it onto the stack
            if (!isOperator(token)) {
                stack.push(token);
            } else {
                try {
                    // Token is an operator: pop top two entries
                    Double d2 = Double.valueOf(stack.pop());
                    Double d1 = Double.valueOf(stack.pop());
                    // Get the result and push onto the stack
                    stack.push(String.valueOf(getOperator(token).eval(d1, d2)));
                } catch (NumberFormatException | ArithmeticException e) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return Double.valueOf(stack.pop());
    }

    public void registerOperator(Operator op) {
        checkNotNull(op);
        operators.add(op);
        parser.registerOperator(op);
    }

    public void registerOperators(Set<Operator> opTable) {
        operators = opTable;
        parser.registerOperatorTable(opTable);
    }

    public Operator getOperator(String token) {
        for (Operator op : operators)
            if (op.getSimbol().compareTo(token) == 0)
                return op;
        return null;
    }

    public boolean isOperator(String symbol) {
        return getOperator(symbol) != null;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("operators", operators)
                .toString();
    }
}
