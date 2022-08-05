package me.sleepydeveloper.springstompchattingdemo.security;

import me.sleepydeveloper.springstompchattingdemo.domain.account.AccountRepository;
import me.sleepydeveloper.springstompchattingdemo.security.service.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/account/sign-up").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .successHandler((request, response, authentication) -> response.sendRedirect("/home"))
                .failureHandler((request, response, exception) -> response.sendRedirect("/"));

        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountRepository accountRepository) {
        return new CustomUserDetailsService(accountRepository);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
                .antMatchers("/h2-console/**", "/static/**", "/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }
}
