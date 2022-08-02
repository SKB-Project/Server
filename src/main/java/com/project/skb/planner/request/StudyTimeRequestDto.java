package com.project.skb.planner.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StudyTimeRequestDto {

    private LocalTime wakeUpTime; // 기상 시간
    private LocalTime startTime; // 공부 시작 시간
    private LocalTime endTime; // 공부 종료 시간
}
