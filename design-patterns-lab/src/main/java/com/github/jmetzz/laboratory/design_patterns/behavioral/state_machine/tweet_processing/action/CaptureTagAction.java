package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action;


import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.OutputStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine.State;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Captures characters adding them to a ByteArrayOutputStream until it detects a space or the input buffer is empty
 */
public class CaptureTagAction extends AbstractAction<State>{

    private final ByteArrayOutputStream tagStream;

    private final byte[] buf;

    private final OutputStrategy output;

    private boolean terminating;

    public CaptureTagAction(OutputStream os, OutputStrategy output) {
        super(os);
        tagStream = new ByteArrayOutputStream();
        buf = new byte[1];
        this.output = output;
    }

    /**
     * Process a byte using this action
     *
     * @param b
     *            The byte to process
     * @param currentState
     *            The current state of the state machine
     */
    @Override
    public State processByte(byte b, State currentState) throws Exception {

        State retVal = currentState;

        if (b == ' ') {
            retVal = State.READY; // fix 1
            output.build(tagStream.toString(), os);
            if (!terminating) {
                super.writeByte(' ');
            }
            reset();
        } else {
            buf[0] = b;
            tagStream.write(buf);
        }

        return retVal;
    }

    /**
     * Reset the object ready for processing
     */
    public void reset() {
        terminating = false;
        tagStream.reset();
    }

    @Override
    public void terminate(State state) throws Exception {
        terminating = true;
        processByte((byte) ' ', state);
    }

}
