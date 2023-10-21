package com.example.demo.security.jwt;

import com.example.demo.security.userpincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "ducdz@codegym.vn";
    private int jwtExpiration = 86400;
    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpiration*1000)).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Ivalid JWT sinature -->Message: {}", e);
        } catch (MalformedJwtException e) {
            logger.error("The token invaild format ->Message:{}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported Jwt token ->Message:{}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired Jwt token ->Message:{}", e);
        } catch (IllegalArgumentException e) {
            logger.error("Jwt claims string is empty ->Message:{}", e);
        }
        return false;
    }
    public String getUserNameFromToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
        return userName;
    }
}
