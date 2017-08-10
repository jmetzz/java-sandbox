package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action;


import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine.State;

import java.io.OutputStream;

public class ReadyAction extends AbstractAction<State> {


    public ReadyAction(OutputStream os) {
        super(os);
    }

    @Override
    public State processByte(byte b, State currentState) throws Exception {

        switch (b) {
            case '#': return State.HASHTAG;
            case '@': return State.NAMETAG;
            case 'h': return State.HTTPCHECK;
            default:
                super.writeByte(b); //consumes and continue
                return State.RUNNING;
        }

    }
}
