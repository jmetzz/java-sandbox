package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine;


import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.AbstractAction;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StateMachine<T extends Enum<?>> {

    private final byte[] inputBuffer = new byte[32768];

    private T currentState;

    private final Map<T, AbstractAction<T>> stateActionMap = new HashMap<>();

    public StateMachine(T startState) {
        this.currentState = startState;
    }

    public void processStream(InputStream in) throws StateMachineException {
        try {
            processBuffers(in);
            terminate();
        } catch (Exception ioe) {
            throw new StateMachineException("Error processing input stream: " + ioe.getMessage(), ioe);
        }
    }


    private void processBuffers(InputStream in) throws Exception {
        for (int len = in.read(inputBuffer); (len != -1); len = in.read(inputBuffer)) {
            // process the buffer:
            for (int i = 0; i < len; i++) {
                processByte(inputBuffer[i]);
            }
        }
    }

    /**
     * Deal with each individual byte in the buffer
     */
    private void processByte(byte b) throws Exception {
        // Get the set of actions associated with this state
        AbstractAction<T> action = stateActionMap.get(currentState);
        // do the action, get the next state
        currentState = action.processByte(b, currentState);
    }

    /**
     * The buffer is empty. Make sue that we tidy up
     */
    private void terminate() throws Exception {
        AbstractAction<T> action = stateActionMap.get(currentState);
        action.terminate(currentState);
    }

    /**
     * Add an action to the machine and associated state to the machine.
     * A state can have more than one action associated with it
     */
    public void addAction(T state, AbstractAction<T> action) {
        // FIX ME : use multimap
        stateActionMap.put(state, action);
    }



}
