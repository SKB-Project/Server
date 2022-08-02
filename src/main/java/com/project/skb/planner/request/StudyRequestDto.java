package com.project.skb.planner.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;


@Getter
@NoArgsConstructor
public class StudyRequestDto {

    private List<TodayStudyRequestDto> todayStudyRequestDtoList;
    private StudyTimeRequestDto studyTimeRequestDto;

    @Builder
    public StudyRequestDto(List<TodayStudyRequestDto> todayStudyRequestDtoList, StudyTimeRequestDto studyTimeRequestDto){
        this.todayStudyRequestDtoList = todayStudyRequestDtoList;
        this.studyTimeRequestDto = studyTimeRequestDto;
    }
}
