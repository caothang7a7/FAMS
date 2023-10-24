package com.backend.FAMS.controller.User.security;/*  Welcome to Jio word
    @author: Jio
    Date: 10/15/2023
    Time: 8:27 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.security.AuthenticationResponse;
import com.backend.FAMS.dto.security.RefreshTokenRequest;
import com.backend.FAMS.service.sercutity.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class SecurityController {

    private final RefreshTokenService refreshTokenService;
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest, BindingResult bindingResult) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.ok(refreshTokenService.verifyRefreshToken(refreshTokenRequest, bindingResult) );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
