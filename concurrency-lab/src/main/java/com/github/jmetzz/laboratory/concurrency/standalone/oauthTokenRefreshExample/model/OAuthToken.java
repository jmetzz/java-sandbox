package com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.model;

import java.util.Calendar;
import java.util.Date;

public class OAuthToken {
    // The OAuth2.0 Access Token
    private String accessToken;
    // The OAuth2.0 Refresh Token
    private String refreshToken;
    // The date this token will expire
    private Calendar expirationDate;

	public OAuthToken() {}
	
	public boolean isValid(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 5);
		if (cal.before(expirationDate)) {
		    return true;
		} else {
		    return false;
		}		
	}
    
    public void setAccessToken(String accessToken){
    	this.accessToken=accessToken;
    }

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}
    
	public String toString(){
		return "AccessToken: " + this.accessToken +
				"\nRefreshToken: " + this.refreshToken + 
				"\nExpires on: " + this.expirationDate.getTime().toString();
	}

	public void setExpiration(Calendar calendar) {
		this.expirationDate = calendar;		
	}
    
    
    // TODO: Constructor + Getters & Setters + other methods
}