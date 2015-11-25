package com.github.jmetzz.services;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.client.HttpResponseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Jean Metz.
 */
public class HttpFetcherTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(18089);

    private HttpFetcher instance;

    @Before
    public void init() {
        instance = new HttpFetcher();

        stubFor(get(urlEqualTo("/hoge.txt")).willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("hoge")
        ));

        stubFor(get(urlEqualTo("/500.txt")).willReturn(
                aResponse().withStatus(500).withHeader("Content-Type", "text/plain").withBody("hoge")));

        stubFor(get(urlEqualTo("/503.txt")).willReturn(
                aResponse().withStatus(503).withHeader("Content-Type", "text/plain").withBody("hoge")));
    }

    @Test
    public void ok() throws Exception {
        String actual = instance.fetchAsString("http://localhost:18089/hoge.txt");

        String expected = "hoge";
        assertThat(Objects.equals(actual, expected));
    }

    @Test(expected = HttpResponseException.class)
    public void notFound() throws Exception {
        instance.fetchAsString("http://localhost:18089/NOT_FOUND");
    }

    @Test(expected = HttpResponseException.class)
    public void internalServerError() throws Exception {
        instance.fetchAsString("http://localhost:18089/500.txt");
    }

    @Test(expected = HttpResponseException.class)
    public void serviceUnavailable() throws Exception {
        instance.fetchAsString("http://localhost:18089/503.txt");
    }
}