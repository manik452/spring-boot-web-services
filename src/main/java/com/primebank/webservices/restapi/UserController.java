package com.primebank.webservices.restapi;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping(path = "/user")
    public String getUser(@Validated @RequestBody User user) {
        return "All User";
    }

    @GetMapping(path = "/all-user")
    public String getAllUser() {
        return "All User";
    }

    @GetMapping(path = "/user/{id}")
    public String getUserById(@PathVariable int id) {
        /*Headoas*/
        return "Your Id" + String.valueOf(id);
    }
}
