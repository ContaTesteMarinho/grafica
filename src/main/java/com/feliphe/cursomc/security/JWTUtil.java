package com.feliphe.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	public String generaToken(String username) {
		
		Long timeMilliSeconds = System.currentTimeMillis() + Long.parseLong(expiration);
		
		return Jwts.builder()
				   .setSubject(username)
				   .setExpiration(new Date(timeMilliSeconds))
				   .signWith(SignatureAlgorithm.HS512, secret.getBytes())
				   .compact();
	}
}
