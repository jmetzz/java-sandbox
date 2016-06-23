package com.github.jmetzz.laboratory.design_patterns.creational.factory;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.AbstractCalculator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.core.BasicCalculator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.IEvaluator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.evaluator.PostfixEvaluator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Division;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Operator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Sum;
import org.assertj.core.data.Offset;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;


public class AbstractFactoryTest {


    @Test
    public void shouldCreateCalculators()  {

        IEvaluator evaluator = new PostfixEvaluator();

        AbstractCalculator basicCalculator = (new BasicCalculatorFactory(evaluator)).createCalculator();
        assertThat(basicCalculator).isNotNull();
        assertThat(basicCalculator.calculate("3 + 9")).isEqualTo(12.0);

        AbstractCalculator superCalculator = (new SuperCalculatorFactory(evaluator)).createCalculator();
        assertThat(superCalculator).isNotNull();
        assertThat(superCalculator.calculate("2 ^ 3")).isEqualTo(8.0);
        assertThat(superCalculator.calculate("4 R 2")).isEqualTo(2.0, Offset.offset(0.0000001));

    }

}