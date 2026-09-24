package com.iam.controller;

import com.iam.dto.RefreshTokenRequest;
import com.iam.entity.RefreshToken;
import com.iam.service.JWTService;
import com.iam.service.RefreshTokenService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final JWTService jwtService;

    public RefreshTokenController(
            RefreshTokenService refreshTokenService,
            JWTService jwtService) {

        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    @PostMapping("/refresh-token")
    public Map<String, String> refreshToken(
            @RequestBody RefreshTokenRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.findByToken(
                        request.getRefreshToken()
                );

        refreshTokenService.verifyExpiration(refreshToken);

        String accessToken =
                jwtService.generateToken(
                        refreshToken.getUser().getEmail()
                );

        return Map.of(
                "accessToken", accessToken
        );
    }
}