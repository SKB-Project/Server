package com.project.skb.user.controller;

import com.project.skb.ResponseDto;
import com.project.skb.user.request.SignInRequestDto;
import com.project.skb.user.request.SignUpRequestDto;
import com.project.skb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseDto userSignUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        return userService.userSignUp(signUpRequestDto);
    }

    @PostMapping("/signIn") // "RequestBody"에 유효성 검증하기 위해서 "@Valid" 입력
    public ResponseDto userSingIn(@Valid @RequestBody SignInRequestDto signInRequestDto){
        return userService.userSignIn(signInRequestDto);
    }

    @DeleteMapping("/quit/{email}")
    public ResponseDto userQuit(@PathVariable String email){
        return userService.userQuit(email);
    }


}
