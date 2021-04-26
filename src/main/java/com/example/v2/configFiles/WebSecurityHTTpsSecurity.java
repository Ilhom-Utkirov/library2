package com.example.v2.configFiles;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//
// @EnableWebSecurity
public class WebSecurityHTTpsSecurity {//extends WebSecurityConfigurerAdapter {
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/hello").permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .and().formLogin();


    }
*/
}

