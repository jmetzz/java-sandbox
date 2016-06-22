package com.github.jmetzz.laboratory.demo.generic_calculator.evaluator;

import com.github.jmetzz.laboratory.demo.generic_calculator.operators.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class PostfixEvaluatorTest {


    private static PostfixEvaluator evaluator;
    private String input;
    private Double expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1 + 2 + 3", 6.0},
                {"2*(3+5)", 16.0},
                {"(3+5)*2+(6-3)", 19.0},
                {"( 1 + 2       ) * ( 4 / 2 ) - ( 5 + 6 )", -5.0},
                {"(41.5-2)*3-3+8/8/(1+1)", 116.0}
        });

    }

    @BeforeClass
    public static void setup() {

        Set<Operator> operators = new HashSet<>();
        operators.add(new Sum());
        operators.add(new Sub());
        operators.add(new Multiplication());
        operators.add(new Division());
        operators.add(new Mod());

        evaluator = new PostfixEvaluator();
        evaluator.registerOperators(operators);

    }

    public PostfixEvaluatorTest(String input, Double expected) {
        this.input = input;
        this.expected = expected;
    }


    @Test
    public void shouldEvaluate() {
        assertThat(evaluator.evaluate(input)).isEqualTo(expected);
    }
}