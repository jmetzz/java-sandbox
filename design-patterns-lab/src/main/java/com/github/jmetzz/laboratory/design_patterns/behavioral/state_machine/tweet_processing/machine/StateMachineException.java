package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine;

public class StateMachineException extends RuntimeException {


    public StateMachineException() {
        super();
    }

    public StateMachineException(String s) {
        super(s);
    }

    public StateMachineException(String s, Throwable cause) {
        super(s, cause);
    }
}
