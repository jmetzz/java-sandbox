package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing;


import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.CaptureTagAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.CheckHttpAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.DefaultAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.ReadyAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.HashTagStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.OutputStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.UrlStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.UserNameStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine.State;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine.StateMachine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class StateMachineAppDemo {

    public static void main(String[] args) {
        StateMachine<State> machine = new StateMachine<>(State.OFF);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Map the actions to the corresponding states:
        machine.addAction(State.OFF, new DefaultAction(outputStream));
        machine.addAction(State.RUNNING, new DefaultAction(outputStream));
        machine.addAction(State.READY, new ReadyAction(outputStream));
        machine.addAction(State.HASHTAG, new CaptureTagAction(outputStream, new HashTagStrategy()));
        machine.addAction(State.NAMETAG, new CaptureTagAction(outputStream, new UserNameStrategy()));
        machine.addAction(State.HTTPCHECK, new CheckHttpAction(outputStream));
        machine.addAction(State.URL, new CaptureTagAction(outputStream, new UrlStrategy()));

        // process the input stream
        String input = "Lorem Ipsum is simply @dummy text of the printing and typesetting industry (from: http://www.lipsum.com/)." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                " when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into #electronic #typesetting, remaining essentially unchanged." +
                " It was popularised in the 1960s with the release of @Letraset sheets containing Lorem Ipsum passages," +
                " and more recently with desktop publishing software like @Aldus PageMaker including versions of Lorem Ipsum.";

        machine.processStream(new ByteArrayInputStream(input.getBytes()));

        System.out.println(outputStream.toString());
    }
}
