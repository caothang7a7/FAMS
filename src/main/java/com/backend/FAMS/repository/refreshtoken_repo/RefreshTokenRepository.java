package com.backend.FAMS.repository.refreshtoken_repo;

import com.backend.FAMS.entity.refreshtoken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findByTokenAndActiveTrue(String refreshToken);

    RefreshToken findByActiveTrueAndUser_Email(String email);
}
