package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.machine;

import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.CaptureTagAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.CheckHttpAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.DefaultAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.ReadyAction;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.HashTagStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.UrlStrategy;
import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.UserNameStrategy;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class StateMachineTest {

    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws Exception {
        outputStream = new ByteArrayOutputStream();
    }

    private void doTest(String tweet, String expected) {

        StateMachine<State> machine = new StateMachine<>(State.OFF);

        // Add some actions to the statemachine

        // Add the default action
        machine.addAction(State.OFF, new DefaultAction(outputStream));
        machine.addAction(State.RUNNING, new DefaultAction(outputStream));
        machine.addAction(State.READY, new ReadyAction(outputStream));
        machine.addAction(State.HASHTAG, new CaptureTagAction(outputStream, new HashTagStrategy()));
        machine.addAction(State.NAMETAG, new CaptureTagAction(outputStream, new UserNameStrategy()));
        machine.addAction(State.HTTPCHECK, new CheckHttpAction(outputStream));
        machine.addAction(State.URL, new CaptureTagAction(outputStream, new UrlStrategy()));

        // process the input stream
        machine.processStream(new ByteArrayInputStream(tweet.getBytes()));

        String result = outputStream.toString();
        assertEquals(expected, result);
    }

    @Test
    public void testTweetToHtmlWithNoTags() throws IOException {
        String expected = "Just seen Hunger Games with 14 year old who was seeing it for the second time in a week! Liked it, Catniss Everdeen is a good heroine";
        doTest(expected, expected);
    }

    @Test
    public void testTweetToHtmlWithSpaceTermniatedNameTag() throws IOException {

        String tweet = "RT @monkchips The Currency Cloud Secures $4M To Disrupt FX Market ";
        String expected = "RT <a href=\"https://twitter.com/#!/monkchips\">@monkchips</a> The Currency Cloud Secures $4M To Disrupt FX Market ";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithTwoSpaceTermniatedNameTag() throws IOException {

        String tweet = "RT @monkchips The Currency Cloud @Secures $4M To Disrupt FX Market ";
        String expected = "RT <a href=\"https://twitter.com/#!/monkchips\">@monkchips</a> The Currency Cloud <a href=\"https://twitter.com/#!/Secures\">@Secures</a> $4M To Disrupt FX Market ";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithSpaceTermniatedHashTag() throws IOException {

        String tweet = "Nick Clegg in Crewe to welcome 500 new jobs being created and 200 protected BentleyMotors #regionalgrowthfund hello";
        String expected = "Nick Clegg in Crewe to welcome 500 new jobs being created and 200 protected BentleyMotors <a href=\"https://twitter.com/#!/search/%23regionalgrowthfund\">#regionalgrowthfund</a> hello";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithTwoSpaceTermniatedHashTag() throws IOException {

        String tweet = "Nick Clegg in Crewe to welcome 500 new #jobs being created and 200 protected BentleyMotors #regionalgrowthfund hello";
        String expected = "Nick Clegg in Crewe to welcome 500 new <a href=\"https://twitter.com/#!/search/%23jobs\">#jobs</a> being created and 200 protected BentleyMotors <a href=\"https://twitter.com/#!/search/%23regionalgrowthfund\">#regionalgrowthfund</a> hello";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlOfHashTag() throws IOException {

        String tweet = "Nick Clegg in Crewe to welcome 500 new jobs being created and 200 protected BentleyMotors #regionalgrowthfund";
        String expected = "Nick Clegg in Crewe to welcome 500 new jobs being created and 200 protected BentleyMotors <a href=\"https://twitter.com/#!/search/%23regionalgrowthfund\">#regionalgrowthfund</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithTwoHashTag() throws IOException {

        String tweet = "Nick Clegg in Crewe to welcome 500 new #jobs being created and 200 protected BentleyMotors #regionalgrowthfund";
        String expected = "Nick Clegg in Crewe to welcome 500 new <a href=\"https://twitter.com/#!/search/%23jobs\">#jobs</a> being created and 200 protected BentleyMotors <a href=\"https://twitter.com/#!/search/%23regionalgrowthfund\">#regionalgrowthfund</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithNameTag() throws IOException {

        String tweet = "RT @monkchips";
        String expected = "RT <a href=\"https://twitter.com/#!/monkchips\">@monkchips</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithJustANameTag() throws IOException {

        String tweet = "@monkchips";
        String expected = "<a href=\"https://twitter.com/#!/monkchips\">@monkchips</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithJustAHashTag() throws IOException {

        String tweet = "#regionalgrowthfund";
        String expected = "<a href=\"https://twitter.com/#!/search/%23regionalgrowthfund\">#regionalgrowthfund</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetToHtmlWithHashTagAtStart() throws IOException {

        String tweet = "#regionalgrowthfund hello";
        String expected = "<a href=\"https://twitter.com/#!/search/%23regionalgrowthfund\">#regionalgrowthfund</a> hello";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetWithURL() throws IOException {

        String tweet = "My Twizy review will be on Fully Charged http://bit.ly/d2FkjM soon, well, week after next. Quite soon";
        String expected = "My Twizy review will be on Fully Charged <a href=\"http://bit.ly/d2FkjM\">bit.ly/d2FkjM</a> soon, well, week after next. Quite soon";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetWithURLAtEnd() throws IOException {

        String tweet = "My Twizy review will be on Fully Charged http://bit.ly/d2FkjM";
        String expected = "My Twizy review will be on Fully Charged <a href=\"http://bit.ly/d2FkjM\">bit.ly/d2FkjM</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetWithURLAtStart() throws IOException {

        String tweet = "http://bit.ly/d2FkjM soon, well, week after next. Quite soon";
        String expected = "<a href=\"http://bit.ly/d2FkjM\">bit.ly/d2FkjM</a> soon, well, week after next. Quite soon";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetWithURLOnly() throws IOException {

        String tweet = "http://bit.ly/d2FkjM";
        String expected = "<a href=\"http://bit.ly/d2FkjM\">bit.ly/d2FkjM</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetWithURLAndHashTag() throws IOException {

        String tweet = "My Twizy #jobs review will be on Fully Charged http://bit.ly/d2FkjM";
        String expected = "My Twizy <a href=\"https://twitter.com/#!/search/%23jobs\">#jobs</a> review will be on Fully Charged <a href=\"http://bit.ly/d2FkjM\">bit.ly/d2FkjM</a>";
        doTest(tweet, expected);
    }

    @Test
    public void testUrlEndsWithFullStop() throws IOException {

        String tweet = "http://bit.ly/d2FkjM.  Hello";
        String expected = "<a href=\"http://bit.ly/d2FkjM\">bit.ly/d2FkjM</a>.  Hello";
        doTest(tweet, expected);
    }

    @Test
    public void testTweetWithMulitpleTypes() throws IOException {

        String tweet = "Deputy PM Nick Clegg visits #Bentley today to tour Manufacturing facilities. #RegionalGrowthFund http://t.co/kX81aZmY http://t.co/Eet31cCA";
        String expected = "Deputy PM Nick Clegg visits <a href=\"https://twitter.com/#!/search/%23Bentley\">#Bentley</a> today to tour Manufacturing facilities. <a href=\"https://twitter.com/#!/search/%23RegionalGrowthFund\">#RegionalGrowthFund</a> <a href=\"http://t.co/kX81aZmY\">t.co/kX81aZmY</a> <a href=\"http://t.co/Eet31cCA\">t.co/Eet31cCA</a>";
        doTest(tweet, expected);
    }

}