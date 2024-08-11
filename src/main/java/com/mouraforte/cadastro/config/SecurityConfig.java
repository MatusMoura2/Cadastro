package com.mouraforte.cadastro.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String[] PUBLIC_MATCHES = { "/h2-console/**" };
	
	@Autowired
	private Environment env;

	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
            httpSecurity.headers(headers -> headers.frameOptions().disable());
		}
		httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "home").permitAll()
						.requestMatchers(PUBLIC_MATCHES).permitAll().requestMatchers(HttpMethod.GET, "/api/**")
						.permitAll().requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN").anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll());
		return httpSecurity.build();
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
}
