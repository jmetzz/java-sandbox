package com.github.jmetzz.apachecommons.httpClient3;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LegacyHttpClientDemo {

    private static final Logger LOGGER = Logger.getLogger(LegacyHttpClientDemo.class);

    private static final String SERVICE_URI = "put.your.resource.path.here";
    public static final String HTTP_AUTHORIZATION_METHOD = "Basic ";
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String REQUEST_PARAMETER_DISTRIBUTOR = "distributor";
    public static final String REQUEST_PARAMETER_RESOURCE_ID = "id";
    public static final String REPLY_PARAMETER_SUCCESS = "success";

    public static void main(String[] args) {
        LegacyHttpClientDemo demo = new LegacyHttpClientDemo();

        String userId = "user";
        String password = "01234";
        String resourceId = "01";
        String distributor = "UPS";

        JSONObject jsonReply = demo.makePostRequest(userId, password, resourceId, distributor);
        boolean success = (Boolean.parseBoolean((String) jsonReply.get(REPLY_PARAMETER_SUCCESS)))
                && StringUtils.equals(resourceId, (String) jsonReply.get(REQUEST_PARAMETER_RESOURCE_ID));

        LOGGER.info("Fetch resource succeeded: " + success);

    }

    private JSONObject makePostRequest(String userName, String password, String resourceID, String distributor) {

        JSONObject reply;
        try {
            HttpClient client = new HttpClient();
            HttpMethod post = new PostMethod();
            post.setURI(new HttpURL(SERVICE_URI));

            NameValuePair[] params = new NameValuePair[]{
                    new NameValuePair(REQUEST_PARAMETER_RESOURCE_ID, resourceID),
                    new NameValuePair(REQUEST_PARAMETER_DISTRIBUTOR, distributor)
            };

            post.setQueryString(params);
            post.setRequestHeader("ACCEPT", CONTENT_TYPE_APPLICATION_JSON);
            post.setRequestHeader("AUTHORIZATION", HTTP_AUTHORIZATION_METHOD + encryptCredentials(userName, password));
            post.setRequestHeader("CONNECTION", "Keep-Alive");

            client.executeMethod(post);

            if (post.getStatusCode() == HttpStatus.SC_OK) {
                String responseBodyAsString = post.getResponseBodyAsString();
                LOGGER.debug("Response for resource " + resourceID + " is: " + responseBodyAsString);
                try {
                    return (JSONObject) (new JSONParser()).parse(responseBodyAsString);
                } catch (ParseException e) {
                    LOGGER.info("Unable to parse reply for resource " + resourceID);
                    return createFailureReply();
                }
            } else {
                LOGGER.info("Failed due to bad request to the service.");
                reply = createFailureReply();
            }

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Failed due to: Invalid encoding");
            reply = createFailureReply();
        } catch (IOException e) {
            LOGGER.error("Failed due to: IO error, could not connect to the service");
            reply = createFailureReply();
        }

        return reply;
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


}
