package com.harriahola.json.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.harriahola.json.api.security.jwt.JwtAuthenticationFilter;
import com.harriahola.json.api.security.jwt.JwtUtil;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebConfiguration webConfiguration;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        

        // use the application.properties app.enableJwtAuthentication
        if (webConfiguration.isEnabledJwtAuthentication()) {
            // use JWT Authentication
            http.csrf().disable()
            .cors().configurationSource(CorsConfigurationSource())
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/generate-token/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        } else {
            // do not use JWT
            http.csrf().disable()
            .cors().configurationSource(CorsConfigurationSource())
            .and()
            .authorizeRequests()
            .anyRequest().permitAll();
        }
    }


    private CorsConfigurationSource CorsConfigurationSource() {
        return null;
    }

}
