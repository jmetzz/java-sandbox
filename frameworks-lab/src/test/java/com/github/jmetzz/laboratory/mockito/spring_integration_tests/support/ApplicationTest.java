package com.github.jmetzz.laboratory.mockito.spring_integration_tests.support;

import com.github.jmetzz.laboratory.mockito.spring_integration_tests.config.TestApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.*;

/**
 * Created by jean on 6/02/2017.
 */

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ContextConfiguration(classes = TestApplicationContext.class)
//@WebAppConfiguration
@EnableAspectJAutoProxy
@ComponentScan()
public @interface ApplicationTest {

}
