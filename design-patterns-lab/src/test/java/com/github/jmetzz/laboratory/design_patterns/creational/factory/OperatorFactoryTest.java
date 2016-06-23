package com.github.jmetzz.laboratory.design_patterns.creational.factory;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Division;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Operator;
import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Sum;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;


public class OperatorFactoryTest {

    @Test
    public void shouldInstantiateOperator() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Operator sumOp = OperatorFactory.getOperator(Sum.OPERATOR_SUM, "++");

        assertThat(sumOp).isNotNull().isInstanceOf(Sum.class);
        assertThat(sumOp.getSimbol()).isEqualTo("++");

        Operator divOp = OperatorFactory.getOperatorGeneric(Division.class, "#");

        assertThat(divOp).isNotNull().isInstanceOf(Division.class);
        assertThat(divOp.getSimbol()).isEqualTo("#");
    }

}