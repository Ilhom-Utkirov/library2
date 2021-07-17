package com.example.v2.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorController {
    @RequestMapping("/403")
    public String accessDenied(){
        return "errors/403";
    }

}
