package com.sevitours.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/clients/**").authenticated()
                .anyRequest().permitAll()
                )
                .requestCache(cache -> {
                    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
                    requestCache.setMatchingRequestParameterName(null);
                    cache.requestCache(requestCache);
                })
                .formLogin(form -> form.permitAll());

        http.csrf((csrf) -> {
            csrf.ignoringRequestMatchers("/h2-console/**");
        });

        http.headers((headers) ->
                headers.frameOptions((opts) -> opts.disable()));
        return http.build();

    }

}
