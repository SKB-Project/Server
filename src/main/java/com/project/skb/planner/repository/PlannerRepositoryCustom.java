package com.project.skb.planner.repository;

import com.project.skb.planner.response.GetStudyTimeResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface PlannerRepositoryCustom {

    List<GetStudyTimeResponseDto> getStudyTimeDay(Long userId, LocalDate date); // 해당 일자 조회
    List<GetStudyTimeResponseDto> getStudyTimeMonth(Long userId, LocalDate startDate, LocalDate endDate); // 해당 월 조회

}
