package com.github.jmetzz.wiremock;


import com.github.jmetzz.wiremock.testsupport.WireMockTestClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


/**
 */
public class AcceptanceTestBase {

    protected static WireMockServer wireMockServer;
    protected static WireMockTestClient testClient;

    @BeforeClass
    public static void setupServer() {
        setupServer(wireMockConfig());
    }

    @AfterClass
    public static void serverShutdown() {
        wireMockServer.stop();
    }

    public static void setupServer(WireMockConfiguration options) {
        if (options.portNumber() == Options.DEFAULT_PORT) {
            options.dynamicPort();
        }

        wireMockServer = new WireMockServer(options);
        wireMockServer.start();
        testClient = new WireMockTestClient(wireMockServer.port());
        WireMock.configureFor(wireMockServer.port());
    }

    @Before
    public void init() throws InterruptedException {
        WireMock.resetToDefault();
    }
}
