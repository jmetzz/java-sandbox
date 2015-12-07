package com.github.jmetzz.wiremock;

import com.github.jmetzz.wiremock.testsupport.TestHttpHeader;
import com.github.jmetzz.wiremock.testsupport.WireMockTestClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.http.HttpEntity;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Jean Metz.
 */
public class WireMockTest {

    protected static WireMockServer wireMockServer;
    protected static WireMockTestClient testClient;


    @BeforeClass
    public static void setup() {
        WireMockConfiguration options = wireMockConfig();
        setupServer(options);
        setupClient(wireMockServer.port());
    }

    private static void setupServer(WireMockConfiguration options) {
        if (options.portNumber() == Options.DEFAULT_PORT) {
            options.dynamicPort();
        }
        wireMockServer = new WireMockServer(options);
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
    }

    private static void setupClient(final int port) {
        testClient = new WireMockTestClient(port);
    }

    @AfterClass
    public static void teardown() {
        wireMockServer.stop();
    }

    @Before
    public void reset() throws InterruptedException {
        WireMock.resetToDefault();
    }

    @Test
    public void exampleTest() throws IOException {

        givenThat(get(urlEqualTo("/some/thing"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>Some content</response>"))
        );

        getRequestedFor(urlEqualTo("/some/thing"));

        assertThat(testClient.get("/some/thing", new TestHttpHeader("Accept", "text/xml")).statusCode()).isEqualTo(200);
        assertThat(testClient.get("/some/thing/else").statusCode()).isEqualTo(404);

        /*verify(postRequestedFor(urlMatching("/some/thing/[a-z0-9]+"))
                .withRequestBody(matching(".*<message>1234</message>.*"))
                .withHeader("Content-Type", notMatching("application/json")));*/
    }

    @Test
    public void exactUrlOnly() {
        givenThat(get(urlEqualTo("/some/thing"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello world!")));

        assertThat(testClient.get("/some/thing").statusCode()).isEqualTo(200);
        assertThat(testClient.get("/some/thing/else").statusCode()).isEqualTo(404);
    }

    @Test
    public void xpathWithNamespaces() {
        stubFor(put(urlEqualTo("/namespaced/xpath"))
                .withRequestBody(matchingXPath("/stuff:outer/stuff:inner[.=111]")
                        .withXPathNamespace("stuff", "http://foo.com"))
                .willReturn(aResponse().withStatus(200)));
    }

}
