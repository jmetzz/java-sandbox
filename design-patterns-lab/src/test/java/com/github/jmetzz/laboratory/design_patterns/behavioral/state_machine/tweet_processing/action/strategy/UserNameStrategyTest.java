package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;


public class UserNameStrategyTest {

    private UserNameStrategy instance;

    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {

        out = new ByteArrayOutputStream();
        instance = new UserNameStrategy();
    }

    @Test
    public void testURLConstruction() throws IOException {

        instance.build("jmetzz", out);

        String result = out.toString();
        assertEquals("<a href=\"https://twitter.com/#!/jmetzz\">@jmetzz</a>", result);
    }
}