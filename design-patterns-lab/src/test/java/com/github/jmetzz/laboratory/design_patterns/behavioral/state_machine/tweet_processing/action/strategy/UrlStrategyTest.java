package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class UrlStrategyTest {

    public static final String EXPECTED_GOOGLE_URL = "<a href=\"http://www.google.com\">www.google.com</a>";
    private static UrlStrategy instance;

    private static ByteArrayOutputStream out;

    private String input;
    private final String expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new String[][]{
                {"www.google.com", EXPECTED_GOOGLE_URL},
                {"www.google.com;", EXPECTED_GOOGLE_URL + ";"},
                {"www.google.com.", EXPECTED_GOOGLE_URL + "."},
                {"www.google.com,", EXPECTED_GOOGLE_URL + ","}
        });
    }

    public UrlStrategyTest(String input, String expected){
        this.input = input;
        this.expected = expected;
    }

    @BeforeClass
    public static void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        instance = new UrlStrategy();
    }

    @After
    public void cleanStream(){
        out.reset();
    }

    @Test
    public void shouldGenerateURL() throws IOException {

        instance.build(input, out);
        String actual = out.toString();
        assertEquals(expected, actual);
    }

}