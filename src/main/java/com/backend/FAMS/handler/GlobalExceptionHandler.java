package com.backend.FAMS.handler;


import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.exception.ApplicationException;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.exception.OptimisticException;
import com.backend.FAMS.exception.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception ex) {
        return ex.getMessage();
    }

    Map<String, String> error = new HashMap<>();

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleApplicationException(ApplicationException ex) {
        error.put("errorCode", "500");
        error.put("errorStatus", "INTERNAL_SERVER_ERROR");
        error.put("errorMessage", ex.getMessage());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleNotFoundException(NotFoundException ex) {

        error.put("errorCode", "404");
        error.put("errorStatus", "NOT_FOUND_USER");
        error.put("errorMessage", ex.getMessage());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResponse handleTokenExpiredException(TokenExpiredException ex) {
        error.put("errorCode", "403");
        error.put("errorStatus", "UNAUTHORIZED");
        error.put("errorMessage", ex.getMessage());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }


    @ExceptionHandler(OptimisticException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse handleOptimisticException(OptimisticException ex) {
        error.put("errorCode", "409");
        error.put("errorStatus", "CONFLICT");
        error.put("errorMessage", ex.getMessage());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }


}

