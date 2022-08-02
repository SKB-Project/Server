package com.project.skb.planner.domain;

import com.project.skb.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Planner{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planner_id")
    private Long plannerId;

    @CreatedDate
    @Column(name = "date")
    private LocalDate date;
    /*
    * 오늘의 공부 시간 기록, @CreatedDate 어노테이션으로 인해 엔티티 생성시 자동으로 시간 등록
    * 다른 엔티티에서도 "LocalDate"를 사용할 경우 "@MappedSuperClass"의 "BaseEntity" 고려
    * */

    @ElementCollection
    @CollectionTable(name = "study_list", joinColumns = @JoinColumn(name = "planner_id"))
    private List<TodayStudy> todayStudyList = new ArrayList<>();

    @Embedded
    private StudyTime studyTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Builder
    public Planner(List<TodayStudy> todayStudyList, StudyTime studyTime, User user){
        this.todayStudyList = todayStudyList;
        this.studyTime = studyTime;
        this.user = user;
    }
}
