package com.example.v2.configFiles;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityWithAuthenticationManagerBuilder extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("pass")
                .roles("ADMIN")
                .and()
                .withUser("user1")
                .password("pass1")
                .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http.authorizeRequests()
                //.antMatchers("/**").hasRole("ADMIN")
                .antMatchers("/admin").hasRole( "ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/hello").permitAll()
                .and().formLogin();


    }

    @Bean
    public PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();}
}
