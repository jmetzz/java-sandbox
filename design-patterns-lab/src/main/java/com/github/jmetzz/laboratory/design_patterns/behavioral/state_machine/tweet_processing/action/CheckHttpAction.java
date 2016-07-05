package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action;


import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine.State;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class CheckHttpAction extends AbstractAction<State> {

    private static final String CHECK = "ttp://";

    private int pos;

    private final ByteArrayOutputStream tagStream;

    private final byte[] buf;

    public CheckHttpAction(OutputStream os) {
        super(os);
        tagStream = new ByteArrayOutputStream();
        buf = new byte[1];
    }

    /**
     * Process a byte using this action
     *
     * @param b            The byte to process
     * @param currentState The current state of the state machine
     */
    @Override
    public State processByte(byte b, State currentState) throws Exception {

        State retVal = currentState;

        if (CHECK.charAt(pos++) != b) {
            retVal = returnToRunning(b);
            reset();
        } else if (pos == CHECK.length()) {
            retVal = State.URL;
            reset();
        } else {
            buf[0] = b;
            tagStream.write(buf);
        }

        return retVal;
    }

    private State returnToRunning(byte b) throws Exception {
        writeByte('h');
        os.write(tagStream.toByteArray());
        writeByte(b);
        return State.RUNNING;
    }

    /**
     * Reset the object ready for processing
     */
    public void reset() {
        pos = 0;
        tagStream.reset();
    }

    @Override
    public void terminate(State state) throws Exception {
        returnToRunning((byte) ' ');
    }
}
