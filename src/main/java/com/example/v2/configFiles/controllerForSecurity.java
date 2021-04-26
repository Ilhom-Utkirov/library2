package com.example.v2.configFiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class controllerForSecurity {

    @GetMapping("/hello")
    public String mainMenu(){
        return "<h2> Welcome to the main menu everyone is allowed to enter</h2>";
    }

    @GetMapping("/user")
    public String mainUserMenu() {
        return "<h2> Welcome to the main User menu all user and admins are allowed to enter</h2>";
    }

    @GetMapping("/admin")
    public String mainAdminMenu() {
        return "<h2> Welcome to the main Admin menu only admins are allowed to enter</h2>";
    }


}
