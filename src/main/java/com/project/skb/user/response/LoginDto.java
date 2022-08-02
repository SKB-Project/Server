package com.project.skb.user.response;

import lombok.Data;

@Data
public class LoginDto {
    private Long userId;
    private TokenDto tokenDto;
    // login시 userid 넘겨주기 위함
}
