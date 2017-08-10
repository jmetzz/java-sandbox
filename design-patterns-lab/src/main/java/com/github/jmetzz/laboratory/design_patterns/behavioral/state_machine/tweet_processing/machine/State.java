package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine;


public enum State {

    OFF("Off, not running"),
    RUNNING("Running"),
    READY("Ready"),
    HASHTAG("#Hashtag found"),
    NAMETAG("@Name found"),
    HTTPCHECK("Check for url that start with http://"),
    URL("http:// found") ;

    private final String description;

    State(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "State: " + description;
    }
}
