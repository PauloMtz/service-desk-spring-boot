package com.sistema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer
                .antMatchers("/img/**", "/js/**", "/css/**").permitAll()
                .antMatchers("/", "/home", "/usuario/alterasenha/**").permitAll()
                .antMatchers("/cliente/**", "/usuario/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated())
            .formLogin(formLoginCustomizer -> formLoginCustomizer
                .loginPage("/login")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home")
                .permitAll())
            .logout(logoutCustomizer -> logoutCustomizer.logoutSuccessUrl("/home"))
            .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                .accessDeniedPage("/acesso-negado"))
            .csrf(csrfCustomizer -> csrfCustomizer.disable());
            
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
