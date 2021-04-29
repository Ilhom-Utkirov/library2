package com.example.v2.configFiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableWebSecurity
public class WebSecurityWithAuthenticationManagerBuilder {//extends WebSecurityConfigurerAdapter {
/*
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

 */
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {

      //  String[] resources = new String[]{
        //        "/", "/home","/pictureCheckCode","/include/**",
          //      "/css/**","/icons/**","/images/**","/js/**","/layer/**"
        //};


        http
                .authorizeRequests()
                //.antMatchers("/**").hasRole("ADMIN")
                    // .antMatchers(resources).permitAll()
                    .antMatchers("/", "/index").permitAll()
                    .antMatchers("/admin").hasRole( "ADMIN")
                    .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/hello").authenticated()
                    //.anyRequest().authenticated()
                .and()//only authenticated user are allowed
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .permitAll();


    }

 */

/*
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user22")
                        .password("pass22")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

*/

/*

    @Bean
    public PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();}

        */
}
