package com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy;


import com.github.jmetzz.laboratory.design_patterns.behavioral.state_machine.tweet_processing.action.strategy.OutputStrategy;

import java.io.IOException;
import java.io.OutputStream;

public class HashTagStrategy  implements OutputStrategy {
    /**
     * @see OutputStrategy#build(java.lang.String, java.io.OutputStream)
     */
    @Override
    public void build(String tag, OutputStream os) throws IOException {
        String url = "<a href=\"https://twitter.com/#!/search/%23" + tag + "\">#" + tag + "</a>";
        os.write(url.getBytes());
    }
}
