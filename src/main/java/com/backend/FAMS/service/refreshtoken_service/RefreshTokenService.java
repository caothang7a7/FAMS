package com.backend.FAMS.service.refreshtoken_service;

import com.backend.FAMS.dto.auth_dto.AuthenticationResponse;
import com.backend.FAMS.dto.auth_dto.RefreshTokenRequest;
import com.backend.FAMS.entity.refreshtoken.RefreshToken;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken(String email);
    boolean isRefreshTokenValid(RefreshToken refreshToken, UserDetails userDetails);
    AuthenticationResponse verifyRefreshToken(@Valid RefreshTokenRequest refreshTokenRequest, BindingResult bindingResult);
    boolean isRefreshTokenExpired(RefreshToken refreshToken);
    void deleteRefreshToken(RefreshToken refreshToken);
}
