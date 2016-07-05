package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action;

import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.OutputStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine.State;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;


public class CaptureTagActionTest {

    private CaptureTagAction instance;

    private ByteArrayOutputStream out;

    private OutputStrategy testStrategy;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        testStrategy = new TestStrategy();

        instance = new CaptureTagAction(out, testStrategy);
    }

    @Test
    public void testHashTagCaptureWithSpaceTerminator() throws Exception {

        State result = State.OFF;
        String expected = "hashTag ";
        byte[] bytes = expected.getBytes();
        for (byte b : bytes) {
            result = instance.processByte(b, State.HASHTAG);
            if (result == State.READY) {
                break;
            }
        }

        assertEquals(result, State.READY);
        String resultString = out.toString();
        assertEquals(expected, resultString);
    }

    @Test
    public void testHashTagCaptureWithoutSpaceTerminator() throws Exception {

        State result = State.READY;
        byte[] bytes = "#hashTag".getBytes();
        for (byte b : bytes) {
            result = instance.processByte(b, State.HASHTAG);
        }

        assertEquals(State.HASHTAG, result);

        String resultString = out.toString();
        assertEquals("", resultString);
    }

    @Test
    public void testNameTagCaptureWithSpaceTerminator() throws Exception {

        State result = State.OFF;
        String expected = "nameTag ";
        byte[] bytes = expected.getBytes();
        for (byte b : bytes) {
            result = instance.processByte(b, State.NAMETAG);
            if (result == State.READY) {
                break;
            }
        }

        assertEquals(result, State.READY);
        String resultString = out.toString();
        assertEquals(expected, resultString);
    }

    private class TestStrategy implements OutputStrategy {
        @Override
        public void build(String tag, OutputStream os) throws IOException {
            os.write(tag.getBytes());
        }
    }
}