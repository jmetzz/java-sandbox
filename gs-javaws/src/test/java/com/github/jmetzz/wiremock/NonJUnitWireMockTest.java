package com.github.jmetzz.wiremock;

import com.github.jmetzz.wiremock.testsupport.TestHttpHeader;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by exi853 on 26/11/2015.
 */
public class NonJUnitWireMockTest {

    @Test
    public void dosomething(){

        WireMockServer wireMockServer = new WireMockServer(wireMockConfig()); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();

        givenThat(get(urlEqualTo("/some/thing"))
                        .withHeader("Accept", equalTo("text/xml"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "text/xml")
                                .withBody("<response>Some content</response>"))
        );

//        assertThat(testClient.get("/some/thing", new TestHttpHeader("Accept", "text/xml")).statusCode()).isEqualTo(200);
//        assertThat(testClient.get("/some/thing/else").statusCode()).isEqualTo(404);


        verify(getRequestedFor(urlEqualTo("/some/thing"))
                .withHeader("Accept", equalTo("text/xml")));

        verify(postRequestedFor(urlEqualTo("/verify/this"))
                .withHeader("Content-Type", equalTo("text/xml")));


        wireMockServer.stop();
    }
}
