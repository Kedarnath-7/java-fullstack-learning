package com.northernarc.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHelloPublic() {
        return "Hello, World! This is a public endpoint accessible to everyone.";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String sayHelloUser() {
        return "Hello, user! You are authenticated. You have limited access compared to admin, poor boy";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String sayHelloAdmin() {
        return "Hello, Admin!, You are authenticated. You have full access to the system.";
    }

    @RequestMapping("/underwriter")
    @PreAuthorize("hasRole('UNDERWRITER')")
    public String sayHelloUnderwriter() {
        return "Hello, Underwriter!, You have access to approve/rejected loan applications.";
    }
}

