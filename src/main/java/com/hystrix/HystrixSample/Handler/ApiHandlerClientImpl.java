package com.hystrix.HystrixSample.Handler;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ApiHandlerClientImpl implements ApiHandlerClient {

    @Autowired
    private StaticFeignApi staticFeignApi;

    static final String HX_CIRCUIT_NAME = "persondetail";

    @Override
    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")})
    public String getPersonById() throws InterruptedException {
        String resp = staticFeignApi.getPersonByID();
        return resp.toString();
    }

    @FeignClient(name = "v2-service", url = "http://localhost:8098")
    public static interface StaticFeignApi {
        //This return data in 5000milis.
        @RequestMapping(method = RequestMethod.GET, value = "/v2")
        String getPersonByID();

    }
}
