package com.test.model;

public class AccessToken {
	private String accessToken;
	private long expireTime;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	public AccessToken(String accessToken, String expireIn) {
		super();
		this.accessToken = accessToken;
		
		this.expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}
	/**
	 * 查看是否国企
	 * @return
	 */
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}
}
