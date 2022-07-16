package com.project.skb.planner.repository;

import com.project.skb.planner.response.GetStudyTimeResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface PlannerRepositoryCustom {

    List<GetStudyTimeResponseDto> getStudyTime(Long userId, LocalDate date);

}
