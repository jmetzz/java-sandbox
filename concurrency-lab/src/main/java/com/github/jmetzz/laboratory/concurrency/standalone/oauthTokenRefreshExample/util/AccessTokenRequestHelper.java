package com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.util;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class AccessTokenRequestHelper {
    public static final String REDIRECT_URI_PARAM = "redirect_uri";
    public static final String GRANT_TYPE_PARAM = "grant_type";
    public static final String CODE_PARAM = "code";
    public static final String USERNAME_PARAM = "username";
    public static final String PASSWORD_PARAM = "password";
    public static final String REFRESH_TOKEN_PARAM = "refresh_token";
    public static final String SCOPES_PARAM = "scope";

    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String PASSWORD = "password";

    public static HttpEntity<String> createHttpEntity(String credentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, credentials);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    public static HttpEntity<String> createHttpEntity(String credentials, String data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAcceptCharset(Lists.newArrayList(Charset.forName("UTF-8")));
        headers.setConnection("Keep-Alive");
        if (StringUtils.isNotBlank(data)) {
            headers.setContentLength(data.getBytes().length);
        }

        headers.set(HttpHeaders.AUTHORIZATION, credentials);
        return new HttpEntity<>(data, headers);
    }

    public static String joinParameters(Map<String, String> params) {
        return Joiner.on("&").withKeyValueSeparator("=").join(params);
    }

    public static String buildCredentialsHeader(String clientId, String clientSecret) {
        return "Basic " + Base64.encodeBase64String((clientId + ":" + clientSecret).getBytes());
    }

}
