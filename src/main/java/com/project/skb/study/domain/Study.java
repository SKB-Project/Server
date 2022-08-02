package com.project.skb.study.domain;

import com.project.skb.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "study")
@Entity
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "title")
    private String title;

    @Column(name = "number")
    private Integer number; // "int"형 타입은 데이터베이스 컬럼 초기값인 "null"로 생성 불가능 -> Integer

    @Column(name = "limit_number")
    private Integer limitedNumber;

    @OneToMany(mappedBy = "study") // mappedBy = study(o), user(x), 실제 외래키는 User 클래스의 Study study
    // 연관관계의 비주인(주인X)이기 때문에 mappedBy 추가 -> 실제 외래 키를 관리하는 곳(User)을 알려주기 위함
    // mappedBy~로 연결된 속성은 컬럼이 생기지 않고 오직 읽기만 가능
    private List<User> userList = new ArrayList<User>();

    @Builder
    public Study(String title, int limitedNumber, int number){
        this.title = title;
        this.limitedNumber = limitedNumber;
        this.number = number;
    }

    public void changeNumber(Integer number){
        this.number = number;
    }
}
