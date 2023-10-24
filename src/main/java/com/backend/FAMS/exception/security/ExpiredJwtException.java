package com.backend.FAMS.exception.security;/*  Welcome to Jio word
    @author: Jio
    Date: 10/15/2023
    Time: 1:49 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;

public class ExpiredJwtException extends ClaimJwtException {

    public ExpiredJwtException(Header header, Claims claims, String message) {
        super(header, claims, message);
    }
}
