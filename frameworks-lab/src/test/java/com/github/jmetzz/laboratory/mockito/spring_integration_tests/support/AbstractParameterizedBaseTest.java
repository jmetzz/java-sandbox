package com.github.jmetzz.laboratory.mockito.spring_integration_tests.support;

import junitparams.JUnitParamsRunner;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

/**
 * Created by jean on 6/02/2017.
 */

@RunWith(JUnitParamsRunner.class)
@ApplicationTest
public abstract class AbstractParameterizedBaseTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

}
