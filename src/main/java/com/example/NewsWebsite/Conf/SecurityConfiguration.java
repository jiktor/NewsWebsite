package com.example.NewsWebsite.Conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http
				 .authorizeHttpRequests(auth -> {
					 auth.requestMatchers("/").authenticated();
					 auth.requestMatchers("/login").permitAll();
					 auth.requestMatchers("/register").permitAll();
				 }).formLogin(form -> form
						 .loginPage("/login")
						 .defaultSuccessUrl("/")
						 .loginProcessingUrl("/login")
						 .failureForwardUrl("/login?error")
						 .permitAll())
				 .logout( logout -> logout
						 .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
				 .csrf(AbstractHttpConfigurer::disable)
				 .httpBasic(Customizer.withDefaults());
		 return http.build();
	}

}
