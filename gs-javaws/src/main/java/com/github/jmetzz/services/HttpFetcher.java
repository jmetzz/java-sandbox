package com.github.jmetzz.services;


import org.apache.http.client.fluent.Request;

import java.io.IOException;


/**
 * Created by Jean Metz.
 */
public class HttpFetcher {

    public String fetchAsString(String url) throws IOException {
        return Request.Get(url).execute().returnContent().asString();
    }
}
