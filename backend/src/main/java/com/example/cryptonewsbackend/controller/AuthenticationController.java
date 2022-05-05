package com.example.cryptonewsbackend.controller;

import com.example.cryptonewsbackend.util.JwtUtil;
import com.example.cryptonewsbackend.model.AuthenticationRequest;
import com.example.cryptonewsbackend.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;


	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		final String jwt;

		try{
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			jwt = jwtTokenUtil.generateToken(userDetails);

		} catch (BadCredentialsException e){
			e.printStackTrace();
			throw new BadCredentialsException("username/password: Incorrect");
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());

		}

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
