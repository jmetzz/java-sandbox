package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action;


import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractAction<T extends Enum<?>> {

    /**
     * This is the next action to take - See the Chain of Responsibility Pattern
     */
    protected final AbstractAction<T> nextAction;

    protected final OutputStream os;

    protected final byte[] buffer = new byte[1];

    public AbstractAction(OutputStream os){
        this(null, os);
    }

    public AbstractAction(AbstractAction<T> nextAction, OutputStream os) {
        this.os = os;
        this.nextAction = nextAction;
    }

    /**
     * Call the next action in the chain of responsibility
     *
     * @param b
     *            The byte to process
     * @param state
     *            The current state of the machine.
     */
    protected void callNext(byte b, T state) throws Exception {
        if (nextAction != null) {
            nextAction.processByte(b, state);
        }
    }

    /**
     * Process a byte using this action
     *
     * @param b
     *            The byte to process
     * @param currentState
     *            The current state of the state machine
     *
     * @return The next state
     */
    public abstract T processByte(byte b, T currentState) throws Exception;

    /**
     * Override this to ensure an action tides up after itself and returns to a
     * default state. This may involve processing any data that's been captured
     *
     * This method is called when the input stream terminates
     */
    public void terminate(T currentState) throws Exception {
        // blank
    }

    protected void writeByte(byte b) throws IOException {
        buffer[0] = b; // Write the data to the output directory
        os.write(buffer);
    }

    protected void writeByte(char b) throws IOException {
        writeByte((byte) b);
    }

}
