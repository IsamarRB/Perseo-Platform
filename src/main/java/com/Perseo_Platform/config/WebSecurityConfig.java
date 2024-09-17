package com.Perseo_Platform.config;

import com.Perseo_Platform.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/test/all").permitAll()
                                .requestMatchers("/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/test/admin").hasAuthority("ADMIN")
                                .requestMatchers("/control").hasAuthority("ADMIN")
                                .requestMatchers("/users/**").permitAll()
                                .requestMatchers("/users/get/getAllUsers").hasAuthority("ADMIN")
                                .requestMatchers("/user-courses/post").hasAuthority("ADMIN")
                                .requestMatchers("/user-courses/get/**").permitAll()
                                .requestMatchers("/user-courses/put/{id}").hasAuthority("USER")
                                .requestMatchers("/user-courses/delete/{id}").hasAuthority("ADMIN")
                                .requestMatchers("/carts").hasAuthority("ADMIN")
                                .requestMatchers("/carts/get").hasAuthority("ADMIN")
                                .requestMatchers("/carts/get/{id}").permitAll()
                                .requestMatchers("/carts/**").hasAuthority("ADMIN")
                                .requestMatchers("/carts/{id}/courses").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/payment").hasAuthority("ADMIN")
                                .requestMatchers("/payment/{id}").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/work-experience/**").hasAnyAuthority("ADMIN", "USER")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
