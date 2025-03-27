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

    private static String secretKey;

    @Value("${jwt.secret.key}")
    private String injectedSecretKey;

    @PostConstruct
    public void init() {
        secretKey = injectedSecretKey;
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
                .signWith(getSigningKey())
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static boolean isTokenValid(String token) {
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String getAuthorityFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("authority", String.class);
    }

//    public static String getEmailFromJWT(String jwtToken){
//
//        String[] chunks = jwtToken.split("\\.");
//
//        if (chunks.length < 2){
//            throw new JwtTokenInvalidException(ExceptionMessages.INVALID_JWT.getMessage());
//        }
//
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//        String payload = new String(decoder.decode(chunks[1]));
//
//        JSONObject jsonPayload = new JSONObject(payload);
//
//
//        return jsonPayload.getString("sub");
//
//    }

}