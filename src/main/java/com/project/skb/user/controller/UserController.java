package com.project.skb.user.controller;

import com.project.skb.ResponseDto;
import com.project.skb.user.request.SignInRequestDto;
import com.project.skb.user.request.SignUpRequestDto;
import com.project.skb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
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

    @GetMapping("check/email/{email}") // 회원가입 시 작성한 이메일이 데이터베이스에 중복되어 있으면 회원가입 불가
    // 이메일 형식은 꼭 지켜야 할 것! email@naver.com
    public ResponseDto checkEmail(@PathVariable String email){
        return userService.checkEmail(email);
    }

    @GetMapping("check/userName/{userName}") // 회원가입 시 작성한 아이디가 데이터베이스에 중복되어 있으면 회원가입 불가
    // 여기서 아이디는 웹사이트내에서 사용하는 닉네임 개념(로그인 시에는 이메일, 비밀번호 사용!)
    public ResponseDto checkUserName(@PathVariable String userName){
        return userService.checkUserid(userName);
    }

    @DeleteMapping("/quit")
    public ResponseDto userQuit(ServletRequest request){
        return userService.userQuit(request);
    }


}
