package com.project.skb.user.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class SignInRequestDto {

    @Email(message = "이메일 형식을 확인해주세요")
    private String email;

    @Size(min=4,max=12, message = "4~12 글자의 패스워드를 입력해주세요")
    private String password;

    @Builder
    public SignInRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
