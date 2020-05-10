package com.primebank.webservices.restapi;

import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello Wold";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloworldBean helloWorldbean(Local local) {
        return new HelloworldBean("Hello World Bean");
    }
}
