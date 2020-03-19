package com.routine.www.jwt;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
     
	@Autowired
    private DataSource dataSource;
     
     @Autowired
     private MyUserDetailsService myUserDetailService;
     
     @Autowired
     private JwtRequestFilter jwtRequestFilter;
     
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	 auth.jdbcAuthentication()
         .dataSource(dataSource);
     }
     
     @Override
     protected void configure(HttpSecurity http) throws Exception{
           http.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
           
           http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
     }
     
     @Override
     @Bean
     public AuthenticationManager authenticationManagerBean() throws Exception{
           return super.authenticationManagerBean();
     }
     
     //password를 암호화해서 저장하기 위한 Bean이라고 생각하면 된다.
     @Bean
     public PasswordEncoder passwordEncoder() {
           return NoOpPasswordEncoder.getInstance();
     }
}