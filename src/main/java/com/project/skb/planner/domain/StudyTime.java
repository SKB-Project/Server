package com.project.skb.planner.domain;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class StudyTime {

    @Column(name = "wake_up")
    private LocalTime wakeUpTime; // 기상 시간

    @Column(name = "start_time")
    private LocalTime startTime; // 공부 시작 시간

    @Column(name = "end_time")
    private LocalTime endTime; // 공부 종료 시간

    @Column(name = "total_time")
    private LocalTime totalStudyTime; // 총 공부 시간

    @Builder
    public StudyTime(LocalTime wakeUpTime, LocalTime startTime, LocalTime endTime, LocalTime totalStudyTime){
        this.wakeUpTime = wakeUpTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalStudyTime = totalStudyTime;
    }
}
