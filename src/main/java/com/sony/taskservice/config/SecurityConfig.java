package com.sony.taskservice.config;

import com.sony.taskservice.filter.BaseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private BaseFilter baseFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()
                )
                .addFilterBefore(baseFilter, SecurityContextPersistenceFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)   // ✅ Disable Basic Auth
                .formLogin(AbstractHttpConfigurer::disable); // ✅

        return http.build();
    }
}