package com.example.JobApplicationManager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Static secret key and signing key
    private static String secretKey;
    private static SecretKey signingKey;

    @Value("${jwt.secret.key}")
    private String injectedSecretKey;

    @PostConstruct
    public void init() {
        // Initialize the static secretKey and signingKey
        secretKey = injectedSecretKey;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateToken(User user) {
        String authority = user.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER");

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("authority", authority)
                .expiration(new Date(System.currentTimeMillis() + 172_800_000))
                .signWith(signingKey)
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenValid(String token) {
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public static String getAuthorityFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("authority", String.class);
    }
}
