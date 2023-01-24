package com.maxisoft.fileSystem.config;

import com.maxisoft.fileSystem.security.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService jwtUserDetailsService;


    private static final String USERS_ENDPOINT = "/FileSystem/V2/users/**";
    private static final String EVENTS_ENDPOINT = "/FileSystem/V2/events/**";
    private static final String FILES_ENDPOINT = "/FileSystem/V2/files/**";

    public SecurityConfig(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, USERS_ENDPOINT, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, USERS_ENDPOINT, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, USERS_ENDPOINT, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, USERS_ENDPOINT, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,USERS_ENDPOINT, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("MODERATOR")
                .antMatchers(HttpMethod.POST, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, EVENTS_ENDPOINT, FILES_ENDPOINT).hasRole("MODERATOR")

                .antMatchers(HttpMethod.GET, FILES_ENDPOINT, EVENTS_ENDPOINT).hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
