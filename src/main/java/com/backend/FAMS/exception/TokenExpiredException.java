package com.backend.FAMS.exception;/*  Welcome to Jio word
    @author: Jio
    Date: 10/15/2023
    Time: 1:20 AM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }
}