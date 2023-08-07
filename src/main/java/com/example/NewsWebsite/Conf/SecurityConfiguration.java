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
	//TODO когато приложението бъде завършено, трябва да се оправят нвиатра на достъп
	//-> не идентифициран потребител да може да отвори дадена статияи и да види нейните коментари, но да не може да пише коментари
	@Bean
	public PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http
				 .authorizeHttpRequests(auth -> {
					 auth.requestMatchers("/").authenticated();
					 auth.requestMatchers("/admin").hasRole("ADMIN");
					 auth.requestMatchers("/newArticle").hasRole("AUTHOR");
					 auth.requestMatchers("/login").permitAll();
					 auth.requestMatchers("/about").permitAll();
					 auth.requestMatchers("/register").permitAll();
					 auth.requestMatchers("/article*").authenticated();
					 auth.requestMatchers("/admin*").hasRole("ADMIN");
					 auth.requestMatchers("/home/*").authenticated();
					 auth.requestMatchers("/article/comment*").authenticated();
					 auth.requestMatchers("/admin/deleteUser*").hasRole("ADMIN");
					 auth.requestMatchers("/admin/editUser*").hasRole("ADMIN");
				 }).formLogin(form -> form
						 .loginPage("/login")
						 .defaultSuccessUrl("/home/1?pageNumber=1",true)
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
