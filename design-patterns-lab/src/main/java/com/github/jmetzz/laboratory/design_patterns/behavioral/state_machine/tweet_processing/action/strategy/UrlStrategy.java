package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy;


import java.io.IOException;
import java.io.OutputStream;

public class UrlStrategy implements OutputStrategy {


    public static final String PUNCTUATION_SYMBOLS = ".,;";

    @Override
    public void build(String url, OutputStream os) throws IOException {

        String lastChar = url.substring(url.length() - 1);
        if (isPunctuation(lastChar)) {
            url = url.substring(0, url.length() - 1);
        } else {
            lastChar = null;
        }

        String anchorTag = makeAnchor(url, lastChar);
        os.write(anchorTag.getBytes());
    }

    private boolean isPunctuation(String lastChar) {
        return PUNCTUATION_SYMBOLS.contains(lastChar);
    }

    private String makeAnchor(String url, String lastChar) {
        return lastChar == null ? "<a href=\"http://" + url + "\">" + url + "</a>" : "<a href=\"http://" + url + "\">" + url
                + "</a>" + lastChar;

    }
}
