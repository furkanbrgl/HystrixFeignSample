package com.hystrix.HystrixSample.Config;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultClientConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO: There is something that doesn't work.
 */
public class HttpClientConfiguration {
    public static final String BEAN_NAME_EXT_CALL_HTTP_CLIENT = "extCallHttpClient";

    @Bean
    @ConfigurationProperties(prefix = "httpClient")
    public HttpClientConfig httpClientConfig() {
        return new HttpClientConfig();
    }

    @Bean(name = BEAN_NAME_EXT_CALL_HTTP_CLIENT)
    public CloseableHttpClient extCallHttpClient(HttpClientConfig cc) {
        RequestConfig rc = RequestConfig.custom()//
                .setConnectionRequestTimeout(cc.getConnectTimeout())//
                .setConnectTimeout(cc.getConnectTimeout())//
                .build();

        SocketConfig sc = SocketConfig.custom().setSoTimeout(cc.getReadTimeout()).build();
        HttpClientBuilder builder = HttpClientBuilder.create()//
                .disableAutomaticRetries()//
                .disableCookieManagement()//
                .disableContentCompression()//
                .disableRedirectHandling()//
                .setMaxConnTotal(cc.getMaxConnections())//
                .setMaxConnPerRoute(cc.getMaxConnectionsPerHost())//
                .setDefaultSocketConfig(sc)//
                .setDefaultRequestConfig(rc);

        if (cc.isKeepAlive()) {
            builder.evictExpiredConnections();
            builder.evictIdleConnections(30, TimeUnit.SECONDS);
            builder.setConnectionTimeToLive(cc.getKeepAliveTimeToLiveMs(), TimeUnit.MILLISECONDS);
            builder.setConnectionReuseStrategy(DefaultClientConnectionReuseStrategy.INSTANCE);
        } else {
            List<Header> defaultHeaders = new ArrayList<>(2);
            defaultHeaders.add(new BasicHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE));
            defaultHeaders.add(new BasicHeader(HTTP.CONTENT_LEN, "0"));
            builder.setDefaultHeaders(defaultHeaders);
            builder.setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE);
        }
        return builder.build();//
    }

}
