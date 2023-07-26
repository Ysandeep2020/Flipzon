package com.flipzon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	// Authentication
	public UserDetailsService userDetailsService() {

//		UserDetails admin = User.withUsername("sandeep").password(encoder.encode("sandeep")).authorities("ADMIN")
//				.build();
//		UserDetails user = User.withUsername("hemraj").password(encoder.encode("hemraj")).authorities("ADMIN").build();
//		UserDetails user2 = User.withUsername("ashish").password(encoder.encode("ashish")).authorities("ADMIN").build();
//		return new InMemoryUserDetailsManager(admin, user, user2);

		return new MyUserDetailsService();
	}

	// Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http://localhost:8585/swagger-ui/index.html#/ url
		// /swagger-ui/index.html#/ --- uri
		return http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/api/v1/customers/new", "/api/v1/products/add","/api/v1/customers/authenticate").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/api/v1/**").authenticated().and().formLogin().and().build();

	}

	@Bean
	public PasswordEncoder passwrodEncoder() {
		// NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		var provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwrodEncoder());
		return provider;
	}
	
}
