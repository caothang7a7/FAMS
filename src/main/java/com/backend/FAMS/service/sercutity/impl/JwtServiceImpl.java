package com.backend.FAMS.service.sercutity.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 10/14/2023
    Time: 8:13 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import com.backend.FAMS.exception.security.TokenExpiredException;
import com.backend.FAMS.service.sercutity.IJwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.backend.FAMS.util.Sercurity.Constants.THREE_MIN;
import static com.backend.FAMS.util.Sercurity.Constants.THREE_SECOND;

@Service
public class JwtServiceImpl implements IJwtService {
    private static final String SECRET_KEY = "5367566B59703373357638792F423F4528482B4D6251655468576D5A71347437";

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private JwtParser jwtParser;

    public JwtServiceImpl() {
        // Tạo custom JwtParser để xác minh token
        this.jwtParser = Jwts.parserBuilder()
                .setClock(new Clock() {
                    @Override
                    public Date now() {
                        return new Date();
                    }
                })
                .setAllowedClockSkewSeconds(0)
                .build();
    }

    @Override
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ THREE_SECOND))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    @Override
    public boolean isTokenExpired(String token) {
        System.out.println(extractExpiration(token));
        return extractExpiration(token).before(new Date());
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){

            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);

    }
    @Override
    public Claims extractAllClaims(String token){
            return Jwts
                    .parserBuilder()
                    .setAllowedClockSkewSeconds(-1)
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

    }
    @Override
    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // V2

}
