package com.teamdev.licenseservice.config;

import com.teamdev.licenseservice.jwt.JwtAccessDeniedHandler;
import com.teamdev.licenseservice.jwt.JwtAuthenticationEntryPoint;
import com.teamdev.licenseservice.jwt.JwtSecurityConfig;
import com.teamdev.licenseservice.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/v1/signin").permitAll()
                .antMatchers("/api/v1/signup").permitAll()
                .antMatchers("/v3/api-docs", "/v3/api-docs/swagger-config", "/swagger-ui/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api/v1/license").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/license-product").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/user/{id}").hasRole("ADMIN")

                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
