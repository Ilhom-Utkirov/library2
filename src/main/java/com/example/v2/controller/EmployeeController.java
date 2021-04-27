package com.example.v2.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String goHome( AuthenticationPrincipal authenticationPrincipal){






        return "EmployeeFiles/index";
    }


}
