package com.project.skb.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequestDto {

    @Email(message = "이메일 형식을 확인해주세요")
    private String email;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @Size(min=4,max=12, message = "4~12 글자의 패스워드를 입력해주세요")
    private String password;

    @Builder
    public SignUpRequestDto(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }
}