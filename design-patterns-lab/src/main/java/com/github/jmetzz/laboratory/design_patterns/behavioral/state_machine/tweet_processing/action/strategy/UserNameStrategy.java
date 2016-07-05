package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy;


import java.io.IOException;
import java.io.OutputStream;

public class UserNameStrategy implements OutputStrategy {

    @Override
    public void build(String tag, OutputStream os) throws IOException {

        if (endsWithPunctuation(tag)) {
            os.write(tag.charAt(tag.length() - 1));
            tag = tag.substring(0, tag.length() - 2);
        }
        String url = "<a href=\"https://twitter.com/#!/" + tag + "\">@" + tag + "</a>";
        os.write(url.getBytes());
    }

    private boolean endsWithPunctuation(String tag) {

        boolean retVal = false;
        if (tag.matches("[a-zA-Z\\d]+(\\.|,|;)")) {
            retVal = true;
        }

        return retVal;
    }

}
