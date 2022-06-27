package com.project.skb.user.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.JwtAuthenticationProvider;
import com.project.skb.user.domain.User;
import com.project.skb.user.domain.UserRepository;
import com.project.skb.user.request.SignInRequestDto;
import com.project.skb.user.request.SignUpRequestDto;
import com.project.skb.user.response.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    public ResponseDto userSignUp(SignUpRequestDto signUpRequestDto){
        User user = User.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();

        userRepository.save(user);

        return new ResponseDto("SUCCESS",user.getId());
    }

    public ResponseDto userSignIn(SignInRequestDto signInRequestDto) {
        if (!userRepository.existsByEmail(signInRequestDto.getEmail())) {
            return new ResponseDto("FAIL", "존재하지 않는 이메일입니다.");}

        User user = userRepository.findByEmail(signInRequestDto.getEmail());
        if (!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            return new ResponseDto("FAIL", "비밀번호가 틀렸습니다.");
        }
        // 로그인 할 경우 "AccessToken"과 "RefreshToken"을 "TokenDto"에 넣어 반환
        TokenDto tokenDto = jwtAuthenticationProvider.createToken(user.getEmail(), user.getRoles());

        // 생성된 "RefreshToken"를 "Redis"에 저장, 시간이 만료 되면 자동적으로 삭제
        // key 값: "RT:email",
        redisTemplate.opsForValue().set("RT:"+user.getEmail(),
                tokenDto.getRefreshToken(), tokenDto.getRefreshTokenTime(), TimeUnit.MILLISECONDS);

        return new ResponseDto("SUCCESS", tokenDto);
    }
}
