package com.github.jmetzz.laboratory.general_java.httpClient;


import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpClientOverURLConnectionDemo {


    public static final String ENDPOINT = "http://localhost/service/rest";
    public static final String APPLICATION_JSON = "application/json";

    private static HttpEntity entityFromConnection(HttpURLConnection connection) {
        BasicHttpEntity entity = new BasicHttpEntity();
        InputStream inputStream;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException ioe) {
            inputStream = connection.getErrorStream();
        }
        entity.setContent(inputStream);
        entity.setContentLength(connection.getContentLength());
        entity.setContentEncoding(connection.getContentEncoding());
        entity.setContentType(connection.getContentType());
        return entity;
    }

    private HttpResponse executeRequest1(String param1, String param2) throws IOException {

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty(HttpHeaders.ACCEPT, APPLICATION_JSON);
        String input = "{\"param1\":\"" + param1 + "\", \"param2\":\"" + param2 + "\"}";
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        HttpResponse response = handleResponse(conn);
        conn.disconnect();

        return response;
    }

    private HttpResponse handleResponse(HttpURLConnection connection) throws IOException {

        StatusLine responseStatus = new BasicStatusLine(
                new ProtocolVersion("HTTP", 1, 1),
                connection.getResponseCode(),
                connection.getResponseMessage());

        BasicHttpResponse response = new BasicHttpResponse(responseStatus);
        response.setEntity(entityFromConnection(connection));
        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
            if (header.getKey() != null) {
                Header h = new BasicHeader(header.getKey(), header.getValue().get(0));
                response.addHeader(h);
            }
        }
        return response;
    }

    // Http < 4.3
    private HttpResponse executeRequest2(String userName, String password, String param1, String param2) throws IOException {

        HttpPost post = new HttpPost(ENDPOINT);
        post.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON));
        post.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encryptCredentials(userName, password));
        post.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
        post.setEntity(createInput(param1, param2));

        HttpClient client = new DefaultHttpClient();
        return client.execute(post);
    }

    // Http 4.3+
    private HttpResponse executeRequest3(String userName, String password, String param1, String param2) throws IOException {

        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.post()
                .setUri(ENDPOINT)
                .setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                .setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encryptCredentials(userName, password))
                .setEntity(createInput(param1, param2))
                .build();

        return client.execute(request);
    }

    private String encryptCredentials(String userName, String password) {
        String authString = userName + ":" + password;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        return new String(authEncBytes);
    }


    private StringEntity createInput(String param1, String param2) throws UnsupportedEncodingException {

        JSONObject json = new JSONObject();
        json.put("param1", param1);
        json.put("param2", param2);

        return new StringEntity(json.toString());
    }
}
