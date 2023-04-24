package com.cloud.authservice.adapters.out.security;

import com.cloud.authservice.adapters.out.model.AuthUserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtProvider {
    @Value("${jwt.secret}")
    private String finalKey;

    public Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(finalKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isJwtExpired(String jwt){
        return extractExpiration(jwt).before(new Date());
    }

    public Date extractExpiration(String jwt){
        return extractClaim(jwt, Claims::getExpiration);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public String extractUsername(String jwt){
        try {
            return extractClaim(jwt, Claims::getSubject);
        }catch (Exception e){
            return null;
        }
    }

    public String generateJwt(AuthUserModel userDetails){
        return generateJwt(new HashMap<>(), userDetails);
    }

    public String generateJwt(
            Map<String, Object> extraClaims,
            AuthUserModel userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isJwtValid(String jwt){
        final String username = extractUsername(jwt);
        return (!isJwtExpired(jwt));
    }
}
