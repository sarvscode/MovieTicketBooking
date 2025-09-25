package com.example.MovieTicketBookRealProject.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secretKey}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private Long jwtexpiration;
	
	public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // username stored in token
                .setIssuedAt(new Date(System.currentTimeMillis())) // issued date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // expires in 10 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
	
	public String extractUsername(String jwtToken) {
		
		return extractClaim(jwtToken,Claims::getSubject);
	}
	
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	 
	  private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    private Key getSignKey() {
	        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }
	    
	    // 🔑 Validate token
	    public boolean isTokenValid(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	    }

	    // Check expiration
	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

 	
}
