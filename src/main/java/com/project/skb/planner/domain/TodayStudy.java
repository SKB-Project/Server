package com.project.skb.planner.domain;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class TodayStudy {

    @Column(name = "title")
    private String title; // 오늘 공부 내용, 국어 or 영어 or 수학 ...

    @Column(name = "study_time")
    private LocalTime studyTime; // 문자열로 받고 나중에 문자열을 정수형으로 반환해서 로직 처리

    @Builder
    public TodayStudy(String title, LocalTime studyTime){
        this.title = title;
        this.studyTime = studyTime;
    }
}
