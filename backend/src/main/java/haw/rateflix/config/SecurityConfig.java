package haw.rateflix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration for the RateFlix application.
 * This class configures the security settings, including JWT authentication,
 * password encoding, and HTTP request authorization.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Provides a PasswordEncoder bean for encoding passwords.
     * This bean uses BCrypt hashing algorithm for secure password storage.
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides the AuthenticationManager bean for the application.
     * This bean is used to manage authentication processes.
     * @param authenticationConfiguration
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures the security filter chain for the application.
     * This method sets up the HTTP security, including CSRF protection,
     * request authorization, and session management.
     * It allows unauthenticated access to specific endpoints and requires
     * authentication for all other requests.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors
            .configurationSource(request -> {
                var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                corsConfig.setAllowedOrigins(java.util.List.of("http://localhost:5173"));
                corsConfig.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                corsConfig.setAllowedHeaders(java.util.List.of("*"));
                corsConfig.setAllowCredentials(true);
                return corsConfig;
            })
            )
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/authenticate").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/contents/**").permitAll()
            .requestMatchers(HttpMethod.PUT, "/api/contents/*/upvote").permitAll()
            .requestMatchers(HttpMethod.PUT, "/api/contents/*/downvote").permitAll() 
            .requestMatchers(HttpMethod.DELETE, "/api/contents/*/upvote").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/contents/*/downvote").permitAll()
            .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}