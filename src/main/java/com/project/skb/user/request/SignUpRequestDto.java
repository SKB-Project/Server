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

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @Email(message = "이메일 형식을 확인해주세요")
    private String email;

    @NotBlank(message = "아이디를 입력해주세요") // ?
    private String userName;

    @Size(min=4,max=12, message = "4~12 글자의 패스워드를 입력해주세요")
    private String password;

    @Builder
    public SignUpRequestDto(String name, String email, String userName, String password){
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}
