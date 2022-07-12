package com.project.skb.study.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.exception.domain.StudyNotFoundException;
import com.project.skb.exception.domain.UserNotFoundException;
import com.project.skb.study.domain.Study;
import com.project.skb.study.repository.StudyRepository;
import com.project.skb.study.request.StudyCreateRequestDto;
import com.project.skb.user.domain.User;
import com.project.skb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class StudyService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;

    @Transactional
    public ResponseDto createStudy(ServletRequest request, StudyCreateRequestDto studyCreateRequestDto) {

        Study study = Study.builder()
                .title(studyCreateRequestDto.getTitle())
                .limitedNumber(studyCreateRequestDto.getLimitedNumber())
                .number(1)
                .build();

        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        user.setStudy(study);
        userRepository.save(user); // 스터디를 생성한 "user"도 스터디에 참가

        study.getUserList().add(user);
        studyRepository.save(study); // 영속성 컨텍스트에 생성된 "study"등록

        return new ResponseDto("SUCCESS",study.getStudyId());

    }

    @Transactional
    public ResponseDto joinStudy(ServletRequest request, Long studyId) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Study study = studyRepository.findById(studyId).
                orElseThrow(() -> new StudyNotFoundException());

        if(study.getNumber().equals(study.getLimitedNumber())){
            return new ResponseDto("FAIL","스터디 인원이 초과하였습니다.");
        }
        else {
            study.setNumber(study.getNumber() + 1);
            study.getUserList().add(user);
            studyRepository.save(study);
        }

        user.setStudy(study);
        userRepository.save(user);
        // 해당 "user"는 영속성 컨텍스트에 존재하지 않기 때문에 따로 "save"를 호출해줘야 함
        // 어떠한 엔티티를 "JPA"를 통해서 생성하거나 조회할 때 영속성 컨텍스트에 엔티티 등록


        return new ResponseDto("SUCCESS",user.getId());

    }

    @Transactional // 트랜잭션 어노테이션 걸워줘야 정상적으로 추방
    public ResponseDto banishUser(ServletRequest request, Long userId) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Study study = studyRepository.findById(user.getStudy().getStudyId()).
                orElseThrow(() -> new StudyNotFoundException());

        User banishedUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        if(banishedUser.getStudy().getStudyId() != study.getStudyId()){
            return new ResponseDto("FAIL", "스터디에 해당 회원이 존재하지 않습니다.");
        }

        banishedUser.setStudy(null);
        study.getUserList().remove(banishedUser);

        return new ResponseDto("SUCCESS",user.getId());

    }
}
