package com.project.skb.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpRequestDto {

    @Email(message = "이메일 형식을 확인해주세요")
    private String email;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

}
