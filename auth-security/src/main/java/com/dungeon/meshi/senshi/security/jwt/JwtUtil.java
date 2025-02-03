package com.dungeon.meshi.senshi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

  private static final String SECRET = "your-256-bit-secret-your-256-bit-secret";
  private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
  private static final long EXPIRATION_TIME = 86400000;

  public String generateToken(String username) {
    return Jwts.builder()
      .subject(username)
      .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SECRET_KEY)
      .compact();
  }

  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  public boolean validateToken(String token) {
    return !getClaims(token).getExpiration().before(new Date());
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
      .verifyWith(SECRET_KEY)
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }
}
