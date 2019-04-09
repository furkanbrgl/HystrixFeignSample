package com.hystrix.HystrixSample.Controller;

import com.hystrix.HystrixSample.Handler.ApiHandlerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    ApiHandlerClient apiHandlerClient;

    @RequestMapping(value = "/greet")
    public String greetH() throws InterruptedException {

        String resp = apiHandlerClient.getPersonById();
        System.out.println("LOG IS WRITING:::::::");

        return resp;
    }

}
