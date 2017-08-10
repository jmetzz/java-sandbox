package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;


public class HashTagStrategyTest {

    private HashTagStrategy instance;
    private ByteArrayOutputStream out;

    @Before
    public void setup(){
        out = new ByteArrayOutputStream();
        instance = new HashTagStrategy();
    }


    @Test
    public void shouldGenerateHashTagURL() throws IOException {
        instance.build("hashTag", out);

        String actual = out.toString();
        String expected = "<a href=\"https://twitter.com/#!/search/%23hashTag\">#hashTag</a>";
        assertEquals(expected, actual);
    }

}