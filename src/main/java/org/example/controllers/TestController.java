package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorized")
public class TestController {

    @GetMapping("/say-hello")
    public String sayHello(){
        return "Hello World!";
    }


}
