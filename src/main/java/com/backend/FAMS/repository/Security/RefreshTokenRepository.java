package com.backend.FAMS.repository.Security;

import com.backend.FAMS.entity.Security.RefreshToken;
import com.backend.FAMS.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findByTokenAndActiveTrue(String refreshToken);

    RefreshToken findByActiveTrueAndUser_Email(String email);
}
