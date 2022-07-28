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
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlannerService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;
    private final PlannerRepositoryImpl plannerRepositoryImpl;

    public ResponseDto writeStudyContent(ServletRequest request, WriteStudyTimeRequestDto writeStudyTimeRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Planner planner = Planner.builder()
                .title(writeStudyTimeRequestDto.getTitle())
                .studyTime(writeStudyTimeRequestDto.getStudyTime())
                .build();

        planner.insertUser(user);
        plannerRepository.save(planner);

        return new ResponseDto("SUCCESS",planner.getPlannerId());
    }

    public ResponseDto getStudyTimeDay(GetStudyTimeRequestDto getStudyTimeRequestDto) {
        Long userId = getStudyTimeRequestDto.getUserId();
        LocalDate date = getStudyTimeRequestDto.getDate();

        List<GetStudyTimeResponseDto> result = plannerRepositoryImpl.getStudyTimeDay(userId, date);
        return new ResponseDto("SUCCESS",result);
    }

    public ResponseDto getStudyTimeMonth(GetStudyTimeRequestDto getStudyTimeRequestDto) {
        Long userId = getStudyTimeRequestDto.getUserId();
        LocalDate date = getStudyTimeRequestDto.getDate();

        String tempStartDate = date.toString();
        String tempEndDate = date.toString();

        int tempDayOfMonth = date.getDayOfMonth();

        String oldDayOfMonth = Integer.toString(tempDayOfMonth);
        String newDayOfMonth = Integer.toString(tempDayOfMonth);

        tempStartDate = tempStartDate.replace(oldDayOfMonth, newDayOfMonth);

        if(tempDayOfMonth<10){ // "10"이하의 숫자들의 Date 형태는 01, 02.. 이기에 정수형으로 변환시 앞의 0이 날라감 -> 1,2,3..
            tempEndDate = tempEndDate.replace("0"+oldDayOfMonth,"31");
        }
        else{
            tempEndDate = tempEndDate.replace(oldDayOfMonth,"31");
        }

        LocalDate startDate = LocalDate.parse(tempStartDate, DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(tempEndDate, DateTimeFormatter.ISO_DATE);

        List<GetStudyTimeResponseDto> result = plannerRepositoryImpl.getStudyTimeMonth(userId, startDate, endDate);

        return new ResponseDto("SUCCESS",result);
    }
}
