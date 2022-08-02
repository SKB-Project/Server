package com.project.skb.user.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.user.domain.User;
import com.project.skb.user.response.LoginDto;
import com.project.skb.user.domain.UserRepository;
import com.project.skb.user.request.SignInRequestDto;
import com.project.skb.user.request.SignUpRequestDto;
import com.project.skb.user.response.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;
    private final UserDetailsService userDetailsService;

    @Transactional
    public ResponseDto userSignUp(SignUpRequestDto signUpRequestDto){

        if(userRepository.existsByEmail(signUpRequestDto.getEmail())){
            return new ResponseDto("FAIL","이미 가입되어있는 회원입니다.");
        } // add

        User user = User.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .userName(signUpRequestDto.getUserName())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();

        userRepository.save(user);

        return new ResponseDto("SUCCESS",user.getId());
    }

    public ResponseDto checkEmail(String email) {
        boolean result = userRepository.existsByEmail(email);
        if (result) {
            return new ResponseDto("FAIL", "이미 존재하는 이메일입니다.");
        }
        return new ResponseDto("SUCCESS", "사용가능한 이메일입니다.");
    }

    public ResponseDto checkUserid(String userName) {
        boolean result = userRepository.existsByUserName(userName);
        if (result) {
            return new ResponseDto("FAIL", "이미 존재하는 아이디입니다.");
        }
        return new ResponseDto("SUCCESS", "사용가능한 아이디입니다.");
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

        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(user.getId());
        loginDto.setTokenDto(tokenDto);
        // login시 userid 넘겨주기 위함
        return new ResponseDto("SUCCESS",loginDto);
    }

    @Transactional
    public ResponseDto userQuit(ServletRequest request) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        // 해야할 것
        // user 값이 null일 경우 체크

        if(user.getId() == null){
            return new ResponseDto("FAIL","존재하지 않는 유저입니다!");
        }
        userRepository.delete(user);

        return new ResponseDto("SUCCESS",user.getId());

    }
}
