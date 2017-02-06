package com.github.jmetzz.laboratory.mockito.spring_integration_tests.config;


import com.github.jmetzz.laboratory.mockito.business.Printer;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jean on 6/02/2017.
 */
@Configuration
public class TestApplicationContext {

    @Bean
    public Printer printer(){
        return Mockito.mock(Printer.class);
    }
}
