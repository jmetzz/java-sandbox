package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy;


import java.io.IOException;
import java.io.OutputStream;

public interface OutputStrategy {

    void build(String tag, OutputStream os) throws IOException;

}
