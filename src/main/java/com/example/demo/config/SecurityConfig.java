package com.example.demo.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdaptor {
  @Override
  public void configure(WebSecurity web) throws Exception{
    web.ignoring().antMatchers("/css/**","/js/**","/images/**");
  }
  @Override
  protected void configure(HttpSession http) throws Exception{
    http.cors().and();
    http.csrf().disable();
  }
}

*/