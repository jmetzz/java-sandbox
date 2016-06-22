package com.github.jmetzz.laboratory.demo.generic_calculator.parser;

import com.github.jmetzz.laboratory.demo.generic_calculator.operators.*;
import com.google.common.base.Joiner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(Parameterized.class)
public class PostfixParserTest {

    private static PostfixParser parser;
    private String input;
    private String expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1 + 2 + 3", "1 2 + 3 +"},
                {"2*(3+5)", "2 3 5 + *"},
                {"(3+5)*2+(6-3)", "3 5 + 2 * 6 3 - +"},
                {"( 1 + 2 ) * ( 4 / 2 ) - ( 5 + 6 )", "1 2 + 4 2 / * 5 6 + -"},
                {"(41.5-2)*3-3+8/8/(1+1)", "41.5 2 - 3 * 3 - 8 8 / 1 1 + / +"}

        });

    }

    public PostfixParserTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @BeforeClass
    public static void setup() {
        parser = new PostfixParser();

        parser.registerOperator(new Sum());
        parser.registerOperator(new Sub());
        parser.registerOperator(new Multiplication());
        parser.registerOperator(new Division());
        parser.registerOperator(new Mod());
    }


    @Test
    public void shouldParseExpression() {
        String[] tokens = parser.parse(input);

        String actual = Joiner.on(" ").join(tokens);

        System.out.println("#" + actual + "#");

        assertThat(actual).isEqualTo(expected);
    }

}