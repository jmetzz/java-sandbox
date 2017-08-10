package com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.model.OAuthToken;
import com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.remote.HttpClientHelper;

public class TokenUtil {

	public static OAuthToken requestSysToken() throws IOException {
		OAuthToken token = new OAuthToken();

		String userName = PropertyLoader.getProperty("custom.pip.global.username");
		String pass = PropertyLoader.getProperty("custom.pip.global.password");
		String scopes = PropertyLoader.getProperty("custom.pip.global.token.OAuth.Scopes");
		String clientId = PropertyLoader.getProperty("custom.pip.global.token.OAuth.Client");
		String clientSecret = PropertyLoader.getProperty("custom.pip.global.token.OAuth.Secret");
		String url = PropertyLoader.getProperty("custom.pip.global.token.endpoint");

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		headers.put("Authorization", DatatypeConverter.printBase64Binary((clientId + ":" + clientSecret).getBytes()));

		String urlParameters = "username=" + userName
				+ "&scope=" + scopes
				+ "&grant_type=password"
				+ "&password=" + pass;

		String response = HttpClientHelper.post(url, headers, urlParameters);
		Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(response.toString());

		String aToken = actualObj.get("access_token").asText();
		String rToken = actualObj.get("refresh_token").asText();
		int expiresIn = actualObj.get("expires_in").asInt();

		calendar.add(Calendar.SECOND, expiresIn);

		token.setExpiration(calendar);
		token.setAccessToken(aToken);
		token.setRefreshToken(rToken);

		return token;
	}

	public static OAuthToken refreshSysToken(OAuthToken token) throws IOException {

		String clientId = PropertyLoader.getProperty("custom.pip.global.token.OAuth.Client");
		String clientSecret = PropertyLoader.getProperty("custom.pip.global.token.OAuth.Secret");
		String url = PropertyLoader.getProperty("custom.pip.global.token.endpoint");

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		headers.put("Authorization", "Basic " + DatatypeConverter.printBase64Binary((clientId + ":" + clientSecret).getBytes()));

		String urlParameters = "grant_type=refresh_token"
				+ "&refresh_token=" + token.getRefreshToken();

		String response = HttpClientHelper.post(url, headers, urlParameters);
		Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(response.toString());

		String aToken = actualObj.get("access_token").asText();
		String rToken = actualObj.get("refresh_token").asText();
		int expiresIn = actualObj.get("expires_in").asInt();

		calendar.add(Calendar.SECOND, expiresIn);

		token.setExpiration(calendar);
		token.setAccessToken(aToken);
		token.setRefreshToken(rToken);

		return token;
	}
}
