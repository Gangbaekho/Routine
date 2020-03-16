package com.routine.www.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.routine.www.security.jwt.JwtRequestFilter;



@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	 @Autowired
     private DataSource securityDataSource;
	 
	 @Autowired
	private JwtRequestFilter jwtRequestFilter;
	 
	 @Autowired
	 UserDetailsService userDetailsService;
	 
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           
    	 auth.jdbcAuthentication()
         .dataSource(securityDataSource);
         
     }
	
	 @Override
     protected void configure(HttpSecurity http) throws Exception{
           
           http
           .csrf().disable()
           .authorizeRequests()
           .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
           .antMatchers("/authenticate").permitAll()
           .antMatchers(HttpMethod.POST,"/users").permitAll()
           .anyRequest().authenticated()
           .and()
           .httpBasic();
           
     }
	 
	 @Override
     @Bean
     public AuthenticationManager authenticationManagerBean() throws Exception{
           return super.authenticationManagerBean();
     }
	 
	 @Bean
     public PasswordEncoder getPasswordEncoder() {
           return NoOpPasswordEncoder.getInstance();
     }
}
