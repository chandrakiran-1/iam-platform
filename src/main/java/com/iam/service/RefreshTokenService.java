package com.iam.service;

import com.iam.entity.RefreshToken;
import com.iam.entity.User;
import com.iam.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.security.SecureRandom;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
    }

    // Create a new refresh token
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        byte[] randomBytes = new byte[32];

        new SecureRandom().nextBytes(randomBytes);

        String token = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);

        refreshToken.setToken(token);

        refreshToken.setExpiryDate(
                Instant.now().plusSeconds(7 * 24 * 60 * 60)
        );

        refreshToken.setUser(user);

        return refreshTokenRepository.save(refreshToken);
    }

    // Find refresh token from database
    public RefreshToken findByToken(String token) {

        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Refresh token not found"
                        ));
    }

    // Check whether refresh token is expired
    public RefreshToken verifyExpiration(
            RefreshToken refreshToken) {

        if (refreshToken.getExpiryDate()
                .isBefore(Instant.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new RuntimeException(
                    "Refresh token expired"
            );
        }

        return refreshToken;
    }
}