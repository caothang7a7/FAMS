package com.backend.FAMS.handler;


import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.exception.ApplicationException;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.exception.security.ExpiredJwtException;
import com.backend.FAMS.exception.security.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public String handleException(Exception ex) {
//        return ex.getMessage();
//    }

//    @ExceptionHandler(ApplicationException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ApiResponse handleApplicationException(ApplicationException ex) {
//        Map<String, String> error = new HashMap<>();
//        error.put("errorCode", "500");
//        error.put("errorMessage", "INTERNAL_SERVER_ERROR");
//
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.error(error);
//        return apiResponse;
//    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleNotFoundException(NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", "404");
        error.put("errorMessage", "NOT_FOUND_USER");

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResponse handleTokenExpiredException(TokenExpiredException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", "401");
        error.put("errorMessage", "UNAUTHORIZED");

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResponse handleTokenExpiredExceptionv2(ExpiredJwtException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", "401");
        error.put("errorMessage", "UNAUTHORIZED");

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }


}

