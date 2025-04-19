package com.example.mlms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())  // Disable CSRF for testing
			.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Apply CORS configuration
			.authorizeHttpRequests(auth -> auth
				.anyRequest().permitAll()
			);
		return http.build();
	}

	@Bean
	public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
		var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
		corsConfiguration.addAllowedOrigin("http://localhost:5173"); // Allow all origins
		corsConfiguration.addAllowedMethod("*"); // Allow all HTTP methods
		corsConfiguration.addAllowedHeader("*"); // Allow all headers
		corsConfiguration.setAllowCredentials(true); // Allow credentials
		corsConfiguration.addExposedHeader("Authorization"); // Expose specific headers if needed
		var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}
