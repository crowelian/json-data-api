package com.harriahola.json.api.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	@Value("${crowelian.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateJwtToken(String subject) {
		
		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

    public String validateJwtToken(String token) {
		try {
			Jws<Claims> claimsJwt = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);
            return claimsJwt.getBody().getSubject();
		} catch (Exception e) {
			System.out.println("JWT error: " + e.getMessage());
		} 

		return null;
	}
    
}
