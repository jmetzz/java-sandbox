package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action;


import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine.State;

import java.io.OutputStream;

public class DefaultAction extends AbstractAction<State>{

    public DefaultAction(OutputStream os) {
        super(os);
    }

    @Override
    public State processByte(byte b, State currentState) throws Exception {
        State retVal = State.RUNNING;

        // Switch the current state to READY if a ' ' char is given as input
        if (isSpace(b)) {
            retVal = State.READY;
            writeByte(b);
        } else if (isHashAtStart(b, currentState)) {
            retVal = State.HASHTAG;
        } else if (isNameAtStart(b, currentState)) {
            retVal = State.NAMETAG;
        } else if (isUrlAtStart(b, currentState)) {
            retVal = State.HTTPCHECK;
        } else {
            writeByte(b);
        }

        return retVal;
    }

    private boolean isSpace(byte b) {
        return b == ' ';
    }

    private boolean isHashAtStart(byte b, State currentState) {
        return (currentState == State.OFF) && (b == '#');
    }

    private boolean isNameAtStart(byte b, State currentState) {
        return (currentState == State.OFF) && (b == '@');
    }

    private boolean isUrlAtStart(byte b, State currentState) {
        return (currentState == State.OFF) && (b == 'h');
    }
}
