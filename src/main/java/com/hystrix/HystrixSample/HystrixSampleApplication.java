package com.hystrix.HystrixSample;

import com.hystrix.HystrixSample.Config.HttpClientConfiguration;
import com.hystrix.HystrixSample.Handler.ApiHandlerClient;
import com.hystrix.HystrixSample.Handler.ApiHandlerClientImpl;
import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableHystrix
@EnableFeignClients
@Import({HttpClientConfiguration.class})
public class HystrixSampleApplication {

    @Qualifier(HttpClientConfiguration.BEAN_NAME_EXT_CALL_HTTP_CLIENT)
    @Autowired
    private CloseableHttpClient extCallHttpClient;

    @Bean
    public ApiHandlerClient apiHandlerClient() {
        return new ApiHandlerClientImpl();
    }

    @Bean
    public Client feignClient() {
        return new ApacheHttpClient(extCallHttpClient);
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixSampleApplication.class, args);
    }

}
