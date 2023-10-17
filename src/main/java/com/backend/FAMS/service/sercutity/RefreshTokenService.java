package com.backend.FAMS.service.sercutity;

import com.backend.FAMS.dto.security.AuthenticationResponse;
import com.backend.FAMS.dto.security.RefreshTokenRequest;
import com.backend.FAMS.entity.Security.RefreshToken;
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
