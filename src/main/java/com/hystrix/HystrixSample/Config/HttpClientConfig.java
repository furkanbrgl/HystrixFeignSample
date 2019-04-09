package com.hystrix.HystrixSample.Config;

public class HttpClientConfig {
	private int connectTimeout = 2000;
	private int readTimeout = 2000;
	private int maxConnections = 2048;
	private int maxConnectionsPerHost = 256;
	private boolean keepAlive = true;
	private int keepAliveTimeToLiveMs = 3000;

	public void setKeepAliveTimeToLiveMs(int keepAliveTimeToLiveMs) {
		this.keepAliveTimeToLiveMs = keepAliveTimeToLiveMs;
	}

	public int getKeepAliveTimeToLiveMs() {
		return keepAliveTimeToLiveMs;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public int getMaxConnectionsPerHost() {
		return maxConnectionsPerHost;
	}

	public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
		this.maxConnectionsPerHost = maxConnectionsPerHost;
	}

}
