package com.github.jmetzz.javademos.httpClient;


import org.apache.commons.codec.binary.Base64;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpClientDemo {

    // TODO check this out \
    // 1. http://www.mkyong.com/java/apache-httpclient-examples/
    // 2. http://www.baeldung.com/httpclient-post-http-request

    private static final Logger LOGGER = Logger.getLogger(HttpClientDemo.class);

    private static final String SERVICE_URI = "put.your.resource.path.here";

    public static final String HTTP_AUTHORIZATION_METHOD = "Basic ";
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String REQUEST_PARAMETER_DISTRIBUTOR = "distributor";
    public static final String REQUEST_PARAMETER_RESOURCE_ID = "id";
    public static final String REPLY_PARAMETER_SUCCESS = "success";


    private JSONObject makePostRequest(String userName, String password, String resourceID, String distributor) {

        JSONObject reply;
        try {
            BasicNameValuePair rID = new BasicNameValuePair(REQUEST_PARAMETER_RESOURCE_ID, resourceID);
            BasicNameValuePair rDistributor = new BasicNameValuePair(REQUEST_PARAMETER_DISTRIBUTOR, distributor);

            HttpClient client = HttpClients.custom().build();
            HttpUriRequest request = RequestBuilder
                    .post()
                    .setUri(SERVICE_URI + "?" + rID + "&"+ rDistributor)
                    .setHeader(HttpHeaders.ACCEPT, CONTENT_TYPE_APPLICATION_JSON)
                    .setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON)
                    .setHeader(HttpHeaders.AUTHORIZATION, HTTP_AUTHORIZATION_METHOD + encryptCredentials(userName, password))
                    .setEntity(createRequestPayload())
                    .build();
            HttpResponse response = client.execute(request);
            reply = parseResponse(response);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Abort provisional exchange for resource " + resourceID + "  failed due to: Invalid encoding");
            reply = createFailureReply();
        } catch (IOException e) {
            LOGGER.error("Abort provisional exchange for resource " + resourceID + "  failed due to: IO error, could not connect to the service");
            reply = createFailureReply();
        }

        return reply;
    }

    private StringEntity createRequestPayload() throws UnsupportedEncodingException {

        JSONObject json = new JSONObject();
        json.put("key1", "value1");
        json.put("key2", "value2");

        return new StringEntity(json.toString());
    }


    private JSONObject parseResponse(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {

                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

                /* Alternatively: use BasicResponseHandler
                String responseString = new BasicResponseHandler().handleResponse(response);
                */

                return (JSONObject) (new JSONParser()).parse(responseString);

            } catch (ParseException e) {
                LOGGER.info("Unable to parse reply ");
                return createFailureReply();
            } catch (IOException e) {
                LOGGER.info("Unable to parse reply ");
                e.printStackTrace();
                return createFailureReply();
            }
        } else {
            LOGGER.info("Fail due to bad request to the service.");
            return createFailureReply();
        }
    }

    private String encryptCredentials(String userName, String password) {
        String authString = userName + ":" + password;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        return new String(authEncBytes);
    }

    private JSONObject createFailureReply() {
        JSONObject json = new JSONObject();
        json.put(REPLY_PARAMETER_SUCCESS, false);
        return json;
    }


    public static void main(String[] args) {

        HttpClientDemo demo = new HttpClientDemo();

        String userId = "user";
        String password = "01234";
        String resourceId = "01";
        String distributor = "UPS";

        JSONObject jsonReply = demo.makePostRequest(userId, password, resourceId, distributor);
        boolean success = (Boolean.parseBoolean((String) jsonReply.get(REPLY_PARAMETER_SUCCESS)))
                && StringUtils.equals(resourceId, (String) jsonReply.get(REQUEST_PARAMETER_RESOURCE_ID));

        LOGGER.info("Fetch resource succeeded: " + success);

    }

}
