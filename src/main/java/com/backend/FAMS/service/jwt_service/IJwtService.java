package com.backend.FAMS.service.jwt_service;/*  Welcome to Jio word
    @author: Jio
    Date: 10/14/2023
    Time: 8:13 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJwtService {

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    boolean isTokenValid(String token, UserDetails userDetails);

    Date extractExpiration(String token);

    Claims extractAllClaims(String token);

    Key getSignInKey();

    boolean isTokenExpired(String token);
}
