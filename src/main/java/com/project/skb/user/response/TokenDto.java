package com.project.skb.user.response;

import lombok.Data;

@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenTime;
    private Long refreshTokenTime;
}
