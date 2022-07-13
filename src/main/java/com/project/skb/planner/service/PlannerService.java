package com.project.skb.planner.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.planner.domain.Planner;
import com.project.skb.user.domain.User;
import com.project.skb.planner.repository.PlannerRepository;
import com.project.skb.user.repository.UserRepository;
import com.project.skb.planner.request.WriteStudyTimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class PlannerService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;

    public ResponseDto writeStudyTime(ServletRequest request, WriteStudyTimeRequestDto writeStudyTimeRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Planner planner = Planner.builder()
                .title(writeStudyTimeRequestDto.getTitle())
                .studyTime(writeStudyTimeRequestDto.getStudyTime())
                .build();

        planner.setUser(user);
        plannerRepository.save(planner);

        return new ResponseDto("SUCCESS",planner.getPlannerId());
    }
}
