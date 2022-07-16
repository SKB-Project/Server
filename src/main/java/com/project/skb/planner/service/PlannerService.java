package com.project.skb.planner.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.planner.domain.Planner;
import com.project.skb.planner.repository.PlannerRepositoryImpl;
import com.project.skb.planner.request.GetStudyTimeRequestDto;
import com.project.skb.planner.response.GetStudyTimeResponseDto;
import com.project.skb.user.domain.User;
import com.project.skb.planner.repository.PlannerRepository;
import com.project.skb.user.repository.UserRepository;
import com.project.skb.planner.request.WriteStudyTimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlannerService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;
    private final PlannerRepositoryImpl plannerRepositoryImpl;

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

    public ResponseDto getStudyTime(GetStudyTimeRequestDto getStudyTimeRequestDto) {
        Long userId = getStudyTimeRequestDto.getUserId();
        LocalDate date = getStudyTimeRequestDto.getDate();

        List<GetStudyTimeResponseDto> result = plannerRepositoryImpl.getStudyTime(userId, date);
        return new ResponseDto("SUCCESS",result);
    }
}
