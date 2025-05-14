package com.clinicpulse.auth.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthenticationResponse {

    private String accessToken;

    @Builder.Default
    private String tokenType = "Bearer";

    public static JwtAuthenticationResponse withToken(String token) {
        return JwtAuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }
}
