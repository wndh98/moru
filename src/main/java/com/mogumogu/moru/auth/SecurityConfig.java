package com.mogumogu.moru.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public abstract class SecurityConfig implements WebSecurityConfigurer {
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests();
    http.authorizeHttpRequests();

}
}
