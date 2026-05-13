package com.likelion14.PBL_Spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello, Likelion!";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        String cleanName = name.replace("!", "");
        throw new RuntimeException("Hello, " + cleanName + "!");
    }
}