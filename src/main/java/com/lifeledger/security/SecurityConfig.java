package com.lifeledger.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            // Disable CSRF because we are using JWT
            .csrf(csrf -> csrf.disable())

            // Return 401 when authentication is required
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {

                    response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED
                    );

                    response.setContentType("application/json");

                    response.getWriter().write(
                        "{\"error\":\"Unauthorized\",\"message\":\"JWT token is required\"}"
                    );
                })
            )

            // CORS configuration
            .cors(cors ->
                cors.configurationSource(corsConfigurationSource())
            )

            // JWT = stateless authentication
            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            // API authorization
            .authorizeHttpRequests(auth -> auth

                // Public APIs
                .requestMatchers(
                    "/api/test",
                    "/api/auth/register",
                    "/api/auth/login"
                ).permitAll()

                // Everything else requires JWT
                .anyRequest().authenticated()
            )

            // Run JWT filter before Spring's default authentication filter
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
            List.of("http://localhost:5173")
        );

        configuration.setAllowedMethods(
            List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
            )
        );

        configuration.setAllowedHeaders(
            List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
            "/**",
            configuration
        );

        return source;
    }
}