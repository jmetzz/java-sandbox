package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.parser;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Associativity;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Operator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class PostfixParser extends Parser {

    private static final String NUMBER_REGEX = "^[0-9]+(\\.[0-9]+)?$";

    // Convert infix expression format into reverse Polish notation
    @Override
    public String[] parse(String input) {

        String[] inputTokens = prepareStatement(input).split(" ");
        ArrayList<String> out = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        // For each token
        for (String token : inputTokens) {
            if ((token.compareTo("") == 0) || (token.compareTo(" ") == 0))
                continue;
            // If token is an operator
            if (isOperator(token)) {
                // While stack not empty AND stack top element
                // is an operator
                while (!stack.isEmpty() && isOperator(stack.peek())) {
                    Operator op1 = getOperator(token);
                    Operator peek = getOperator(stack.peek());

                    if ((op1.getAssociativity() == Associativity.LEFT) && (op1.compareTo(peek) <= 0)
                            || (op1.getAssociativity() == Associativity.RIGHT) && (op1.compareTo(peek) < 0)) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                // Push the new operator on the stack
                stack.push(token);
            }
            // If token is a left bracket '('
            else if (token.equals("(")) {
                stack.push(token); //
            }
            // If token is a right bracket ')'
            else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            }
            // If token is a number
            else if (token.matches(NUMBER_REGEX)) {
                if (sep != '.')
                    out.add(token.replace(sep, '.'));
                else
                    out.add(token);
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }
        while (!stack.isEmpty()) {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    private String prepareStatement(final String input) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        for (char c : input.toCharArray()) {
            if ((c >= '0' && c <= '9') || c == '.') {
                sb.append(c);
            } else {
                sb.append(" ").append(c).append(" ");
            }
        }
        return sb.toString().trim().replaceAll("  ", " ");
    }


    public String toString() {
        return PostfixParser.class.getSimpleName();
    }

}
