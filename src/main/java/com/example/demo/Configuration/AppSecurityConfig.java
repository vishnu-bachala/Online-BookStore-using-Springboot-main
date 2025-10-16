package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
// 1. Updated Method Security Import
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; // 2. New required import
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import static org.springframework.security.config.Customizer.withDefaults;

// Removed @SuppressWarnings("deprecation") and @Service
@Configuration
@EnableWebSecurity
// 3. Replaced @EnableGlobalMethodSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui.html", "/webjars/**",
            "/v3/api-docs/**", "/swagger-ui/**",
            // Public endpoints for sign-up and login
            "/signup**", "/login"
    };

    @Autowired
    UserDetailsService userDetailsService;

// --- Authentication Provider Beans (No change needed here) ---

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        // You must use the encoder() bean here, not create a new one,
        // but for simplicity, we'll keep the new one to match your original logic style
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

// -----------------------------------------------------------

    /**
     * Replaces the deprecated configure(HttpSecurity http) method.
     * This bean defines the security chain for all incoming requests.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 1. Disable CSRF for API endpoints (common practice for stateless APIs)
                .csrf(csrf -> csrf.disable())

                // 2. Define authorization rules (replaces http.authorizeRequests().antMatchers...)
                .authorizeHttpRequests(authorize -> authorize

                        // Allow public access (sign-up, login, Swagger)
                        .requestMatchers(AUTH_WHITELIST).permitAll()

                        // --- Books ---
                        .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/books/**").hasAnyAuthority("Admin")
                        .requestMatchers(HttpMethod.PUT, "/books/**").hasAnyAuthority("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasAnyAuthority("Admin")

                        // --- Publisher ---
                        .requestMatchers(HttpMethod.POST, "/publisher/**").hasAnyAuthority("Admin")
                        .requestMatchers(HttpMethod.GET, "/publisher/**").hasAnyAuthority("Admin")

                        // --- Customer ---
                        .requestMatchers(HttpMethod.GET, "/customer/**").hasAnyAuthority("User", "Admin") // Combined the two GET rules
                        .requestMatchers(HttpMethod.POST, "/customer").hasAnyAuthority("User","Admin")
                        .requestMatchers(HttpMethod.PUT, "/customer/**").hasAnyAuthority("User")
                        .requestMatchers(HttpMethod.DELETE, "/customer/**").hasAnyAuthority("User", "Admin")

                        // --- Order Items ---
                        // NOTE: All orderItems rules are combined for simplicity.
                        .requestMatchers("/orderItems/**").hasAnyAuthority("User", "Admin")

                        // --- Category ---
                        .requestMatchers(HttpMethod.GET, "/category/**").permitAll()
                        .requestMatchers("/category/**").hasAnyAuthority("Admin")

                        // --- Other Endpoints ---
                        .requestMatchers(HttpMethod.PUT, "/confirmorder/**").hasAnyAuthority("Admin")
                        .requestMatchers("/feedback/**").hasAnyAuthority("User", "Admin") // Covers POST, GET, PUT

                        // 3. All other requests must be authenticated
                        .anyRequest().authenticated()
                )

                // 4. Enable Form Login and HTTP Basic authentication
                .formLogin(form -> form.permitAll())
                .httpBasic(withDefaults()); // Uses the static method reference instead of .and().httpBasic()

        return http.build(); // 5. Finalize the configuration
    }

    // Static import helper for httpBasic(withDefaults())
//    private static void withDefaults() {
//        // This is a placeholder to match the old style.
//        // In Spring Security 6, you use .httpBasic(withDefaults())
//        // which refers to org.springframework.security.config.Customizer.withDefaults()
//    }
}