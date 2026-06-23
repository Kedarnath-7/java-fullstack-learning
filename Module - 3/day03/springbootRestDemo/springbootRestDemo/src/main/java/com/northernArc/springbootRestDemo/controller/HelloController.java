package com.northernArc.springbootRestDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("")
    public String welcome(){
        return "Welcome to rest using spring boot";
    }

    @RequestMapping("/bye")
    public String bye(){
        return "Hasta la vista!!";
    }
}
