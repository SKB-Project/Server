package com.project.skb.user.controller;

import com.project.skb.ResponseDto;
import com.project.skb.user.request.SignUpRequestDto;
import com.project.skb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseDto signUpController(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        return userService.userSignUp(signUpRequestDto);
    }
}
