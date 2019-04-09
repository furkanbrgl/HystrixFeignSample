package com.hystrix.HystrixSample.Handler;

public interface ApiHandlerClient {

    String getPersonById() throws InterruptedException;

}
