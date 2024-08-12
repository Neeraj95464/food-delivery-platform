package com.ynmio.GatewayServices.util;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    Logger log= LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

//    public String extractRole(String token) {
//        return extractClaim(token, claims -> claims.get("role", String.class));
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }

    // Assuming you receive the token via a request header
    public Boolean validateToken(String token) {
        try {
            log.info("Validating token: " + token);
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.error("JWT token has expired", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid", e);
        } catch (Exception e) {
            log.error("An error occurred while validating the token", e);
        }
        return false;
    }

}


