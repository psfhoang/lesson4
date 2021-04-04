package com.example.codese_spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import javax.xml.crypto.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class JwtTokenProvider {

  private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
  @Value("hoang")
  private String key;
  private long JWT_EXPIRATION = 604800000L;

  public String generateToken(String username) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS256, key)
        .compact();
  }

  public String getUsernameFromToken(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      this.logger.info("SignatureException. The claimsJws JWS signature validation fails.");
    } catch (MalformedJwtException e) {
      this.logger.info("MalformedJwtException. The claimsJws string is not a valid JWS.");
    } catch (ExpiredJwtException e) {
      this.logger.info(
          "ExpiredJwtException. The specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.");
    } catch (UnsupportedJwtException e) {
      this.logger.info(
          "UnsupportedJwtException. The claimsJws argument does not represent an Claims JWS.");
    } catch (IllegalArgumentException e) {
      this.logger.info(
          "IllegalArgumentException. The claimsJws string is null or empty or only whitespace.");
    }
    return false;
  }

}
