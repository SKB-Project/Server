package com.project.skb.user.service;

import com.project.skb.ResponseDto;
import com.project.skb.user.domain.User;
import com.project.skb.user.domain.UserRepository;
import com.project.skb.user.request.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseDto userSignUp(SignUpRequestDto signUpRequestDto){
        User user = User.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .build();

        userRepository.save(user);

        return new ResponseDto("SUCCESS",user.getId());
    }

}
