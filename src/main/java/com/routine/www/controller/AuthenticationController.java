package com.routine.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.routine.www.security.jwt.AuthenticationRequest;
import com.routine.www.security.jwt.AuthenticationResponse;
import com.routine.www.security.jwt.JwtUtil;
import com.routine.www.security.jwt.MyUserDetails;
import com.routine.www.security.jwt.MyUserDetailsService;

@RestController
@CrossOrigin
public class AuthenticationController {
	
	@Autowired
    private MyUserDetailsService userDetailsService;
	 
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
          
          try {
          authenticationManager
          .authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
          }catch(BadCredentialsException e) {
               throw new Exception("Incorrect username or password",e);
          }
          final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
          
          final String jwt = jwtTokenUtil.generateToken(userDetails);
          
          return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
