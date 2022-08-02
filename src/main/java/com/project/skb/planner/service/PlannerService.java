package com.project.skb.planner.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.planner.domain.*;
import com.project.skb.planner.request.GetStudyTimeRequestDto;
import com.project.skb.planner.request.StudyTimeRequestDto;
import com.project.skb.planner.request.TodayStudyRequestDto;
import com.project.skb.planner.response.GetStudyTimeResponseDto;
import com.project.skb.user.domain.User;
import com.project.skb.user.domain.UserRepository;
import com.project.skb.planner.request.StudyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlannerService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;
    private final PlannerRepositoryImpl plannerRepositoryImpl;

    @Transactional
    public ResponseDto writeStudyContent(ServletRequest request, StudyRequestDto studyRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        List<TodayStudyRequestDto> dayDto = studyRequestDto.getTodayStudyRequestDtoList();
        StudyTimeRequestDto studyTimeRequestDto = studyRequestDto.getStudyTimeRequestDto();

        List<TodayStudy> todayStudyList = new ArrayList<>();
        LocalTime totalStudyTime = LocalTime.of(0,0);

        for(TodayStudyRequestDto t : dayDto){

            LocalTime studyTimeOne = t.getStudyTime();

            TodayStudy tempStudy = TodayStudy.builder()
                    .title(t.getTitle())
                    .studyTime(studyTimeOne)
                    .build();

            todayStudyList.add(tempStudy);

            totalStudyTime.plusHours(studyTimeOne.getHour());
            totalStudyTime.plusMinutes(studyTimeOne.getMinute());
        }

        StudyTime studyTime = StudyTime.builder()
                .wakeUpTime(studyTimeRequestDto.getWakeUpTime())
                .startTime(studyTimeRequestDto.getStartTime())
                .endTime(studyTimeRequestDto.getEndTime())
                .totalStudyTime(totalStudyTime)
                .build();

        Planner planner = Planner.builder()
                .todayStudyList(todayStudyList)
                .studyTime(studyTime)
                .user(user)
                .build();

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
