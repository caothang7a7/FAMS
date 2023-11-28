package com.backend.FAMS.service.refreshtoken_service.impl;

import com.backend.FAMS.dto.auth_dto.AuthenticationResponse;
import com.backend.FAMS.dto.auth_dto.RefreshTokenRequest;
import com.backend.FAMS.entity.refreshtoken.RefreshToken;
import com.backend.FAMS.exception.ApplicationException;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.exception.TokenExpiredException;
import com.backend.FAMS.repository.refreshtoken_repo.RefreshTokenRepository;
import com.backend.FAMS.repository.user_repo.UserRepository;
import com.backend.FAMS.service.jwt_service.IJwtService;
import com.backend.FAMS.service.refreshtoken_service.RefreshTokenService;
import com.backend.FAMS.service.user_service.IUserPermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.UUID;

import static com.backend.FAMS.config.Constants.THIRTY_DAY;


@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final IJwtService jwtService;

    private final IUserPermissionService userPermissionService;
    private final UserDetailsService userDetailsService;

    @Override
    public RefreshToken generateRefreshToken(String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByEmail(email).orElseThrow(
                        () -> new NotFoundException("not found user_controller")
                ))
                .token(UUID.randomUUID().toString())
                .active(true)
                .expiried(new Date(System.currentTimeMillis() + THIRTY_DAY))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public boolean isRefreshTokenValid(RefreshToken refreshToken, UserDetails userDetails) {
        final String userEmail = refreshTokenRepository.findByTokenAndActiveTrue(refreshToken.getToken()).getUser().getEmail();
        return (userEmail.equals(userDetails.getUsername()) && !isRefreshTokenExpired(refreshToken));
    }

    @Override
    public AuthenticationResponse verifyRefreshToken(@Valid RefreshTokenRequest tokenRequest, BindingResult bindingResult) {
        // Kiểm tra null cho TokenRequest
        if (tokenRequest == null) {
            throw new NotFoundException("TokenRequest is null");
        }

        // lấy token ra

        final String userEmail;
        final String refreshToken = tokenRequest.getRefreshToken();

        // Kiểm tra null cho jwtService
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new NotFoundException("Access token is null or empty");
        }

        userEmail = refreshTokenRepository.findByTokenAndActiveTrue(refreshToken).getUser().getEmail();


        if (userEmail != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (userEmail.equals(userDetails.getUsername())) {

                RefreshToken refreshTokenEntity = refreshTokenRepository.findByActiveTrueAndUser_Email(userDetails.getUsername());

                // check Expired
                if (isRefreshTokenExpired(refreshTokenEntity)) {
                    deleteRefreshToken(refreshTokenEntity);
                    throw new TokenExpiredException("refresh token is Expired");
                }

                // Kiểm tra bindingResult và refreshToken
                if (!bindingResult.hasErrors() && refreshToken != null && refreshTokenEntity.getToken() != null && !isRefreshTokenExpired(refreshTokenEntity)) {
                    return AuthenticationResponse.builder()
                            .accessToken(jwtService.generateToken(refreshTokenEntity.getUser()))
                            .build();
                }
            }
        }

        throw new ApplicationException("Invalid access token or refresh token");
    }


    @Override
    public boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiried().before(new Date());
    }

    @Override
    public void deleteRefreshToken(RefreshToken refreshToken) {
        //refreshToken.setActive(false);
        //refreshTokenRepository.save(refreshToken);
        refreshTokenRepository.delete(refreshToken);
    }
}
