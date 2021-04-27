package com.example.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"/", "/index"})
    public String getMainPage(){
        return "index";
    }


    @RequestMapping({"/login.html","/login"})
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping("/login-error.html")
    public String getLoginErrorPage(){
        return "login-error";


    }


}
