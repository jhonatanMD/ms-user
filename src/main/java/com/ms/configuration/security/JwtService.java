package com.ms.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@Slf4j
public class JwtService {

  @Value("${spring.jwt-secret}")
  private String secret;
  private static final long TTL_MILLIS = Duration.ofMinutes(10).toMillis();

  public String generateJwt(String name , String email) {
    Claims claims = Jwts.claims();
    claims.put("name", name);
    claims.put("email", email);

    Date now = new Date();

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + TTL_MILLIS))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean validateJwt(String jwt, String nameValue , String emailValue ) {
    try {
      Jws<Claims> claimsJws = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(jwt);

      Object name = claimsJws.getBody().get("name");
      Object email = claimsJws.getBody().get("email");
      if (name instanceof String && email instanceof String) {
        boolean hasPermission = name.equals(nameValue) && email.equals(emailValue);
        log.info("HasPermission: {}", hasPermission);
        return hasPermission;
      }
      return false;
    }catch (Exception e) {
      log.error("Error caught validating JWT", e);
      return false;
    }
  }

}
