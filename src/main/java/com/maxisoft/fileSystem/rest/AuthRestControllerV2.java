package com.maxisoft.fileSystem.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthRestControllerV2 {

    @GetMapping("/login")
    public String loginPage(){
        return "hello";
    }
}
