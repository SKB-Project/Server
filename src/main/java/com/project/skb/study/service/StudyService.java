package com.project.skb.study.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.study.domain.Study;
import com.project.skb.study.repository.StudyRepository;
import com.project.skb.study.request.StudyCreateRequestDto;
import com.project.skb.user.domain.User;
import com.project.skb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class StudyService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;

    public ResponseDto createStudy(ServletRequest request, StudyCreateRequestDto studyCreateRequestDto) {

        Study study = Study.builder()
                .title(studyCreateRequestDto.getTitle())
                .limitedNumber(studyCreateRequestDto.getLimitedNumber())
                .build();

        studyRepository.save(study); // 영속성 컨텍스트에 생성된 "study"등록

        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        user.setStudy(study);
        userRepository.save(user); // 스터디를 생성한 "user"도 스터디에 참가

        return new ResponseDto("SUCCESS",study.getStudyId());

    }

    public ResponseDto joinStudy(ServletRequest request, Long studyId) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Study study = studyRepository.findById(studyId).
                orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디입니다."));

        user.setStudy(study);
        userRepository.save(user);
        // 해당 "user"는 영속성 컨텍스트에 존재하지 않기 때문에 따로 "save"를 호출해줘야 함
        // 어떠한 엔티티를 "JPA"를 통해서 생성하거나 조회할 때 영속성 컨텍스트에 엔티티 등록

        return new ResponseDto("SUCCESS",user.getId());

    }

}
