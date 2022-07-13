package com.project.skb.planner.domain;

import com.project.skb.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Planner{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planner_id")
    private Long plannerId;

    @CreatedDate
    @Column(name = "date")
    private LocalDate date; // 오늘의 공부 시간 기록, @CreatedDate 어노테이션의 특징 때문

    @Column(name = "title")
    private String title; // 오늘 공부 내용, 국어 or 영어 or 수학 ...

    @Column(name = "study_time")
    private String studyTime; // 문자열로 받고 나중에 문자열을 정수형으로 반환해서 로직 처리

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @Builder
    public Planner(String title, String studyTime){
        this.title = title;
        this.studyTime = studyTime;
    }

}
