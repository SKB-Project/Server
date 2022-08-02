package com.project.skb.planner.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class TodayStudyRequestDto {

    private String title; // 오늘 공부 내용, 국어 or 영어 or 수학 ...
    private LocalTime studyTime; // 문자열로 받고 나중에 문자열을 정수형으로 반환해서 로직 처리

}
